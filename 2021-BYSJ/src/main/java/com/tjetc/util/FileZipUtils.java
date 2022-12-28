package com.tjetc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @belongProject:20210601-bysj
 * @belongPackage:com.tjetc.util
 * @author:xujirong
 * @dscription:java压缩成zip
 * @date:2022-08-07 15:09
 * @version:1.0
 */
public class FileZipUtils {

    /**
     * @param inputFileName 你要压缩的文件夹(整个完整路径)
     * @param zipFileName   压缩后的文件(整个完整路径)
     * @descriprion: 描述
     * @author: xujirong
     * @date: 2022/8/7 15:10
     */
    public static boolean zip(String inputFileName, String zipFileName) {

        try {
            zip(zipFileName, new File(inputFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @descriprion:
     * @author: xujirong
     * @date: 2022/8/7 15:49
     * @zipFileName:压缩包路径
     * @inputFile:需要被压缩的文件夹
     * @return: void
     */
    private static void zip(String zipFileName, File inputFile) throws Exception {
        //压缩包输出流对象-将inputFile->压缩到->zipFileName中
        //根据zipFileName->创建压缩包
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, "");
        out.flush();
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {

        if (f.isDirectory()) {
            //判断f是文件，还是多级文件夹---如果是多级文件夹，则往最里层递归
            File[] fl = f.listFiles();
            //System.out.println("fl = " + fl.length);
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            //System.out.println("base.length() = " + base.length());
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }

        } else {
            //f为某种格式文件，不是文件夹new ZipEntry（）:即将写入压缩包的文件路径
            //System.out.println("base = " + base);
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

    public static void main(String[] temp) {
        try {
            zip("D:\\test", "C:\\Users\\ASUS\\Desktop\\test.zip");//你要压缩的文件夹      和  压缩后的文件
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
