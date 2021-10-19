package com.tjetc.demo.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/*
* ftp工具类
* */
public class FtpUtil {

    /**
     * @Description: 传入：ip，用户名，密码，文件路径，文件名称，获取ftp文件返回数组
     */
    public  static ArrayList<String[]> ftpWorks(String ip, String user, String password, String dir, String fileName) {

        // 创建接口服务
        FtpInterface ftpInterface = new Ftp();
        // 登录ftp，获取事件
        //FTPClient ftp1 = ftpInterface.ftp("ftp上的ip地址", "用户名",  "密码");//这里是ip，用户名，密码
        FTPClient ftp1 = ftpInterface.ftp(ip, user, password);//这里是ip，用户名，密码
        //将ftp操作改为被动模式 否则可能有421错误。
        ftp1.enterLocalPassiveMode();
        ArrayList<String[]> csvList = null;
        if (null != ftp1) {
            try {
                // 更改当前工作目录,HC2WL为文件所在的目录
                ftp1.changeWorkingDirectory(dir);
                // 从ftp上获取HC2WL目录下的文件
                FTPFile[] file = ftp1.listFiles();
                // 遍历所有文件，匹配需要查找的文件
                for (int i = 0; i < file.length; i++) {
                    // 匹配到则进入
                    if (file[i].getName().contains(fileName)) {
                        // 将匹配到的文件流传入接口，转化成数组集合
                        csvList = ftpInterface.csv(ftp1.retrieveFileStream(file[i].getName()));
                        //处理数据
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }


    /**
     * Description: 向FTP服务器上传文件
     * @Version      1.0
     * @param url FTP服务器hostname
     * @param port  FTP服务器端口
     * @param username FTP登录账号
     * @param password  FTP登录密码
     * @param path  FTP服务器保存目录
     * @param filename  上传到FTP服务器上的文件名
     * @param input   输入流
     * @return 成功返回true，否则返回false *
     */
    public static boolean uploadFile(String url,// FTP服务器hostname
                                     int port,// FTP服务器端口
                                     String username, // FTP登录账号
                                     String password, // FTP登录密码
                                     String path, // FTP服务器保存目录
                                     String filename, // 上传到FTP服务器上的文件名
                                     InputStream input // 输入流
    ){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(url, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(path);
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * 将本地文件上传到FTP服务器上 *
     */
    public static void upLoadFromProduction(String url,// FTP服务器hostname
                                            int port,// FTP服务器端口
                                            String username, // FTP登录账号
                                            String password, // FTP登录密码
                                            String path, // FTP服务器保存目录
                                            String filename, // 上传到FTP服务器上的文件名
                                            String orginfilename // 输入流文件名
    ) {
        try {
            FileInputStream in = new FileInputStream(new File(orginfilename));
            boolean flag = uploadFile(url, port, username, password,  path,filename, in);
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
