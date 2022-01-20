package com.tjetc.demo.prm数据处理;

import java.io.File;
import java.io.FilenameFilter;

//FilenameFilter实现类  文件接口实现类
public class FRFilenameFilerUtill implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {

        //创建返回值
        boolean flag = false;
        //在已知的文件夹中筛选 某种格式的文件  定义筛选条件
        if (name.toLowerCase().endsWith(".rreq")){
            flag = true;
        }

        return flag;
    }
}
