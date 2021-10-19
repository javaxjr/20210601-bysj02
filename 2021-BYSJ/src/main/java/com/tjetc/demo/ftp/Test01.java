package com.tjetc.demo.ftp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Test01 {

    @Autowired
    private WLToHCService wlToHCService;

    /**
     *  Description: 测试写入csv文件并存储本地，再使用FTP上传
     */
    @Test
    public void SendClueToHC(){
        try {
            wlToHCService.SendClueToHC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
