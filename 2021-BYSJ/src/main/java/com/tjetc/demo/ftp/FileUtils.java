package com.tjetc.demo.ftp;

import java.io.File;
import java.util.List;

public class FileUtils {

    // 删除文件夹下所有文件
    public static void deleteFiles(File rootFile) { //参数是根文件夹
        if (rootFile.listFiles().length == 0) {// 如果用户给定的是空文件夹就退出方法
            return;//退出
        } else {
            File[] files = rootFile.listFiles();// 将非空文件夹转换成File数组
            for (File file : files) {//使用foreach语句遍历文件数组
                if (file.isFile()) {//判断是否为文件
                    file.delete();// 删除指定文件夹下的所有文件
                } else {
                    if (file.listFiles().length == 0) {//file类型是文件夹且文件夹为空
                        file.delete();// 删除指定文件夹下的所有空文件夹
                    } else {
                        deleteDirectories(file);// 删除指定文件夹下的所有非空文件夹（包括file）
                    }
                }
            }
        }
    }

    // 删除文件夹及文件夹下所有文件
    public static void deleteDirectories(File rootFile) {
        if (rootFile.isFile()) {//第一次肯定不是文件类型，因为deleteFiles方法中已经判断过了
            rootFile.delete();// 如果给定的File对象是文件就直接删除
        } else {// 如果是一个文件夹就将其转换成File数组
            File[] files = rootFile.listFiles();// 将非空文件夹转换成File数组
            for (File file : files) {//使用foreach语句遍历文件数组
                deleteDirectories(file);// 如果不是空文件夹则就迭代deleteDictionary()方法
            }
            rootFile.delete();// 如果是空文件夹就直接删除
        }
    }

    // 获得指定目录下的所有文件的路径
    public static List<String> getFilePath(List<String> list, File rootFile) {//返回值的就是传入的List<String> list类型,用于输出被删除的文件
        File[] files = rootFile.listFiles();// 将非空文件夹转换成File数组
        for (File file : files) {//使用foreach语句遍历文件数组
            if (file.isDirectory()) {//判断是否为文件夹
                getFilePath(list, file);//如果是文件夹则就迭代getFilePath()方法
            } else {
                //添加file的绝对路径添加到list中，在 UNIX 系统上，此字段的值为 '/'；在 Microsoft Windows 系统上，它为 '\'
                list.add(file.getAbsolutePath().replace("\\", File.separator));
            }
        }
        //返回所有文件路径,我利用自动生成的文件夹程序，然后再删除发现文本域没输出，原来获得的只是文件路径，我花了半小时找题，被自己蠢哭
        return list;//文件的路径是文件！文件！文件！
    }
}

