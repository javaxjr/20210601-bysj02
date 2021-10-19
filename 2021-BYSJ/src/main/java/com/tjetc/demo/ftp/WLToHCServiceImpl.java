package com.tjetc.demo.ftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 类名称: WLToHCServiceImpl
 * 创建时间:2019/8/20 16:13
 * Version 1.0.0
 */
@Service
public class WLToHCServiceImpl implements WLToHCService {

    @Autowired
    private WLToHCMapper wlToHCMapper;

    //从配置文件中获取文件配置信息
    @Value("${FTPConfiguration.hcftp2.ftpip}")
    private String ip;
    @Value("${FTPConfiguration.hcftp2.port}")
    private int port;
    @Value("${FTPConfiguration.hcftp2.username}")
    private String username;
    @Value("${FTPConfiguration.hcftp2.password}")
    private String password;

    /**
     * @Author kxwang
     * @Description
     * @Date 2019/8/20 16:17
     * @Param []
     * @return void
     */
    public void SendClueToHC() {
        try {
            //1.查询要存储csv的数据
            List<HCClue> hcClueList = wlToHCMapper.queryClueByHK();
            //2.表头
            String[] csvHeaders =  {"POLICE_NAME","CLUE_ID","CASE_CODE","POLICE_CODE","CREATOR",
                    "RESOURCE_TYPE","FILE_NAME","CREATE_TIME","UPDATE_TIME","IS_DELETED","FEATURE"};
            //3.设置本地路径和文件名
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,-1);
            Date time = calendar.getTime();
            String date = new SimpleDateFormat("yyyyMMdd").format(time);
            String filepath = "D:/WL2HC/";//   D:/WL2HC/
            File dirfile = new File(filepath);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            String fileName = "CLUEDATA" + date + ".csv"; //  DATACLUE20190821.csv
            String localFileName = filepath + fileName; //   D:/WL2HC/DATACLUE20190821.csv

            //4.存储csv到本地
            CsvUtil.writeCSV(hcClueList,localFileName,csvHeaders);

            //5.从本地传到ftp
            FtpUtil.upLoadFromProduction(ip,port,username,password,"/WL2HC/",fileName,localFileName);
        } catch (Exception e) {

        }
    }


}
