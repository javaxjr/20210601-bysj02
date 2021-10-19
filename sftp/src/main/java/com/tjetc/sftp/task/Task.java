package com.tjetc.sftp.task;

import com.tjetc.sftp.service.BusCardService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/*
* 首先通过Task调用Service服务登录Ftp服务器，之后在Service类中解析文件。
Task类代码：
* */

@Component
@Slf4j
public class Task {

    @Resource
    private BusCardService busCardService;

    @Scheduled(cron = "${taskCorn}")
    public void getData() {
        busCardService.longinFtp(null);
    }
}

