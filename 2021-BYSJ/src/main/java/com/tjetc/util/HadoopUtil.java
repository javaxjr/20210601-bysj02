package com.tjetc.util;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@RunWith(SpringJUnit4ClassRunner.class)
public class HadoopUtil {

    public static void main(String[] args) {
        double random = Math.ulp(Math.round(5));
        System.out.println("random = " + random);
    }
    //1.连接至hdfs
    @Test
    public void connectHDFS() {
        /*Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(URI.create("hdfs://192.168.58.132:9000"),conf);
            FileStatus fileStatus = fileSystem.getFileLinkStatus(new Path("/user"));
            System.out.println(fileStatus.isDirectory());
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }*/



    }


    //2.创建文件夹
    @Test
    public void mkdir() {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.58.132:9000");
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            boolean mkdirs = fileSystem.mkdirs(new Path("/user/test")); //要创建的文件夹或多级文件夹
            System.out.println(mkdirs?"成功":"失败");
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }


    //3.文件的上传
    //①API直接上传
    @Test
    public void upload1() {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.5.22:9000");//连接hdfs
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            FileInputStream in = new FileInputStream(new File("H:\\test.txt"));//获取本地文件
            FSDataOutputStream out = fileSystem.create(new Path("/upload1.txt")); //选择上传路径
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }


    //②通过Hadoop的IOUtils上传
    @Test
    public void upload2() {
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.5.22:9000"), conf);
            FileInputStream in = new FileInputStream(new File("H:\\test.txt"));//获取本地文件
            FSDataOutputStream out = fileSystem.create(new Path("/upload2.txt")); //选择上传路径
            IOUtils.copyBytes(in, out, conf);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //4.文件下载
    //①API直接下载
    @Test
    public void download1() {
        //添加配置文件
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.5.22:9000"), conf);
            FSDataInputStream in = fileSystem.open(new Path("/upload.txt"));//获取服务器文件
            FileOutputStream out = new FileOutputStream(new File("H:\\download1.txt"));//选择下载路径，下载文件名
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void download1(String s){
        //添加配置文件
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.5.22:9000"), conf);
            FSDataInputStream in = fileSystem.open(new Path("/upload.txt"));//获取服务器文件
            FileOutputStream out = new FileOutputStream(new File("H:\\download2.txt"));//选择下载路径，下载文件名
            IOUtils.copyBytes(in,out,conf);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    //②通过IOUtils下载
    @Test
    public void download2(){
        //添加配置文件
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.5.22:9000"), conf);
            FSDataInputStream in = fileSystem.open(new Path("/upload.txt"));//获取服务器文件
            FileOutputStream out = new FileOutputStream(new File("H:\\download2.txt"));//选择下载路径，下载文件名
            IOUtils.copyBytes(in,out,conf);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //5.修改文件名
    @Test
    public void rename(){
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.5.22:9000"),conf);
            boolean rename = fileSystem.rename(new Path("/upload1.txt"), new Path("/upload3.txt"));
            System.out.println(rename?"修改成功":"修改失败");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //6.遍历所有文件、文件夹
    @Test
    public void download3(){
        //添加配置文件
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.5.22:9000"), conf);
            FSDataInputStream in = fileSystem.open(new Path("/upload.txt"));//获取服务器文件
            FileOutputStream out = new FileOutputStream(new File("H:\\download2.txt"));//选择下载路径，下载文件名
            IOUtils.copyBytes(in,out,conf);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
  //5.修改文件名
    @Test
    public void rename2(){
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.5.22:9000"),conf);
            boolean rename = fileSystem.rename(new Path("/upload1.txt"), new Path("/upload3.txt"));
            System.out.println(rename?"修改成功":"修改失败");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
  //6.遍历所有文件、文件夹
    @Test
    public void lsr() {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.5.22:9000");
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            FileStatus[] listStatus = fileSystem.listStatus(new Path("/"));//获取文件列表
            for (FileStatus fileStatus : listStatus) {
                isDir(fileStatus, fileSystem);//遍历文件列表，判断是文件还是文件夹
            }
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public void isDir(FileStatus fileStatus,FileSystem fileSystem) {
        //如果是文件夹，则获取该文件夹下的文件列表，遍历判断 递归调用
        if(fileStatus.isDirectory()) {
            String dirname = fileStatus.getPath().getName();
            System.out.println("Directory："+dirname);
            FileStatus[] listStatus;
            try {
                listStatus = fileSystem.listStatus(new Path("/"+dirname));
                for (FileStatus fileStatus2 : listStatus) {
                    isDir(fileStatus2,fileSystem);
                }
            } catch (Exception e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }else {
            String dirname = fileStatus.getPath().getName();
            System.out.println("File:"+dirname);
        }
    }





}