package com.tjetc.demo.ftp;


import com.csvreader.CsvReader;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/*
* 实现ftp接口
* */
public class Ftp implements FtpInterface{
    /**
     * <b>登陆ftp 返回ftpClient事件<b>
     * @param ip ftp所在ip
     * @param user 登陆名
     * @param password 密码
     */
    public FTPClient ftp(String ip, String user, String password) {

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            ftpClient.login(user, password);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!ftpClient.isConnected()) {
            ftpClient = null;
        }

        return ftpClient;
    }

    /**
     * <b>将一个IO流解析，转化数组形式的集合<b>
     *
     * @param in 文件inputStream流
     */
    public ArrayList<String[]> csv(InputStream in) {
        ArrayList<String[]> csvList = new ArrayList<>();
        if (null != in) {
            CsvReader reader = new CsvReader(in, ',', Charset.forName("UTF-8"));
            try {
                //遍历每一行，若有#注释部分，则不处理，若没有，则加入csvList
                while (reader.readRecord()) {
                    if (!reader.getValues()[0].contains("#"))// 清除注释部分
                    {
                        //获取的为每一行的信息，以数组的形式
                        csvList.add(reader.getValues());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            reader.close();
        }
        return csvList;
    }

}
