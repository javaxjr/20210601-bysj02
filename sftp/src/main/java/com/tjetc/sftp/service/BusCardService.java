package com.tjetc.sftp.service;

import com.tjetc.sftp.dao.BusCardDao;
import com.tjetc.sftp.entity.BusCard;
import com.tjetc.sftp.util.FtpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BusCardService {

    private static final Logger log = LoggerFactory.getLogger(BusCardService.class);
    /** FTP地址 **/
    @Value("${ftp.ftpAddress}")
    private String ftpAddress;

    /** FTP端口 **/
    @Value("${ftp.ftpPort}")
    private int ftpPort = 0;

    /** FTP用户名 **/
    @Value("${ftp.ftpUsername}")
    private String ftpUsername;

    /** FTP密码 **/
    @Value("${ftp.ftpPassword}")
    private String ftpPassword;

    /** FTP基础目录 **/
    private String basePath = "";

    @Resource
    private BusCardDao busCardDao;

    public boolean longinFtp(String time) {
        FtpUtil ftpUtil = new FtpUtil(ftpAddress, ftpPort, ftpUsername, ftpPassword, basePath);
        Boolean b = ftpUtil.getB();
        if (b) {
            log.info("连接Ftp成功");
        }
        if (StringUtils.isBlank(time)) {
            // 获取当天的时间
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String date = df.format(new Date());
            byte[] bytes = ftpUtil.getFileBytesByName(basePath, date);
            if (!handleBytes(bytes)) {
                return false;
            }
        } else {
            byte[] bytes = ftpUtil.getFileBytesByName(basePath, time);
            if (!handleBytes(bytes)) {
                return false;
            }
        }
        if (ftpUtil.closeConnect()) {
            log.info("Ftp连接关闭");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    boolean handleBytes(byte[] bytes) {
        if (bytes.length == 0) {
            return false;
        }
        String res = null;
        try {
            //此处根据你在ftp上文件的编码自主选择
            res = new String(bytes, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] strings;
        if (res != null) {
            strings = res.split("\r\n");
        } else {
            return false;
        }
        if (strings.length != 0) {
            try {
                List<BusCard> busCardList = formatData(strings);
                busCardDao.saveAll(busCardList);
                log.info("数据采集更新完毕，共" + busCardList.size() + "条");
            } catch (Exception e) {
                log.error(e.getMessage());
                return false;
            }
        }
        return true;
    }

    private List<BusCard> formatData(String[] strings) {
        /**
         * 此处为业务类代码，就是把dat文件中的内容解析成字符串
         * 剩下的就是你自己根据业务情况做解析处理保存到数据库了
         */
        return null;
    }
}

