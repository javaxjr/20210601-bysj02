package com.tjetc.demo.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.InputStream;
import java.util.ArrayList;

/*
* ftp接口
* */
public interface FtpInterface {
    FTPClient ftp(String ip, String user, String password);
    ArrayList<String[]> csv(InputStream in);
}
