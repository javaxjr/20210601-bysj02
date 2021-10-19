package com.tjetc.demo.hadoop;

import java.io.IOException;

import java.net.Socket;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


/**
 * Hello world!
 *
 */
public class AppTest
{

    public static void download() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.159.134:9000");
        FileSystem fs = FileSystem.newInstance(conf);
        fs.copyToLocalFile(new Path("/start-all.sh"),new Path("e://"));
    }

    public static void upload() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.159.134:9000");
        FileSystem fs = FileSystem.get(conf);
        Path src=new Path("d://my.txt");
        Path dest=new Path("/");
        fs.copyFromLocalFile(src,dest);
        FileStatus[] fileStatus  = fs.listStatus(dest);
        for(FileStatus file:fileStatus){
            System.out.println(file.getPath());
        }
        System.out.println("上传成功");
    }

    public static void test()throws Exception{
        Socket socket=new Socket("192.168.159.134",9000);
        System.out.println(socket);//查看网络是否相同，拒绝说明防火墙开了，外界无法访问到网络。关一下就好了
    }



        public static void main(String[] args) {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://192.168.58.132:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            String localDir = "D:\\hadoop\\hadoop-2.6.0\\hadoop-2.6.0\\NOTICE.txt";//本地路径
            String hdfsDir = "/hadoop/input/";//HDFS文件路径
            try{
                Path localPath = new Path(localDir);
                Path hdfsPath = new Path(hdfsDir);
                FileSystem hdfs = FileSystem.get(conf);
                hdfs.copyFromLocalFile(localPath,hdfsPath);
                System.out.println("上传成功");
            }catch(Exception e){
                e.printStackTrace();
            }
        }


}
