package com.tjetc.demo.hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hdfs {

    public static void main(String[] args) {
        Configuration conf = new Configuration();

        // 这个解决hdfs问题
        conf.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
        // 这个解决本地file问题
        conf.set("fs.file.impl", LocalFileSystem.class.getName());

        //conf.set("fs.defaultFS", "hdfs://192.168.58.132:9000");
        try {
            //FileSystem fileSystem = FileSystem.get(conf);
            FileSystem fileSystem=FileSystem.get(URI.create("hdfs://192.168.58.132:9000"),conf,"root");
            boolean mkdirs = fileSystem.mkdirs(new Path("/root/hadoop/test2/")); //要创建的文件夹或多级文件夹
            /*FileInputStream in = new FileInputStream(new File("D:\\hadoop\\hadoop-2.6.0\\hadoop-2.6.0\\NOTICE.txt"));//获取本地文件
            FSDataOutputStream outputStream = fileSystem.create(new Path("/text.txt"));

            IOUtils.copyBytes(in, outputStream, conf);

            in.close();
            outputStream.close();*/
           // boolean b = fileSystem.deleteOnExit(new Path("/root/hadoop/test/text.txt"));
            System.out.println(mkdirs?"成功":"失败");

            //FileStatus fileStatus = fileSystem.getFileLinkStatus(new Path("/user"));
            //System.out.println("哈哈哈哈："+fileStatus.isDirectory());
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        uploadHadoopFile("ali","p","ay");

    }

    //②通过Hadoop的IOUtils上传
    public static void uploadHadoopFile(String a,String b,String c) {

        //文件夹名称
        String mkdirName = a+b.toUpperCase()+c;
        //文件名称
        String fileName=a+b+c;

        //每天生成的文件夹  如 reportTime=20210518
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());


        //添加配置文件
        Configuration conf = new Configuration();

        try {

            // 这个解决hdfs问题
            conf.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
            // 这个解决本地file问题
            conf.set("fs.file.impl", LocalFileSystem.class.getName());


            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.58.132:9000"), conf, "root");

            //获取本地文件
            FileInputStream in = new FileInputStream(new File("C:\\upload\\NOTICE3.txt"));
            //选择上传路径
            FSDataOutputStream out = fileSystem.create(new Path("/root"));

            IOUtils.copyBytes(in, out, conf);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() {
        Configuration conf = new Configuration();

        FileSystem hdfs = null;
        try {
            hdfs = FileSystem.get(URI.create("hdfs://192.168.58.132:9000"),conf,"root");


            //本地文件
            Path src =new Path( "C:\\upload\\NOTICE3.txt" );
            //HDFS为止
            Path dst = new  Path( "/root/test/" );

            hdfs.copyFromLocalFile(src, dst);
            System.out.println( "Uploadto" +conf.get( "fs.default.name" ));

            FileStatus files[]=hdfs.listStatus(dst);
            for (FileStatus file:files){
                System.out.println(file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    //②通过IOUtils下载
    @Test
    public void download2(){

        Configuration conf = new Configuration();
        // 这个解决hdfs问题
        conf.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
        // 这个解决本地file问题
        conf.set("fs.file.impl", LocalFileSystem.class.getName());

        //conf.set("fs.defaultFS", "hdfs://192.168.58.132:9000");
        try {
            FileSystem fileSystem=FileSystem.get(URI.create("hdfs://192.168.58.132:9000"),conf,"root");
            FSDataInputStream in = fileSystem.open(new Path("/root/NOTICE2.txt"));//获取服务器文件

            FileOutputStream out = new FileOutputStream(new File("C:\\upload\\test1.txt"));//选择下载路径，下载文件名
            IOUtils.copyBytes(in,out,conf);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void HHH(){

        Configuration conf = new Configuration();

        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.58.132:9000"), conf, "root");
            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            fileSystem.copyFromLocalFile(false,new Path("C:\\upload\\test1.txt"),new Path("/user/hadoop/weiXin/reportTime="+date));

            //创建目录   在Hadoop系统中创建文件夹
            //fileSystem.create(new Path("/work"));
            //Path path = new Path("/rr/one");
            //fileSystem.mkdirs(path);

            fileSystem.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }


}
