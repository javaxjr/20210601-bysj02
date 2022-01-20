package com.tjetc.demo.prm数据处理;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdatePrm {


    public static void main(String[] args) {
        List<String> stringList = getFileLocalName();
        System.out.println("stringList = " + stringList.size());
        for (String s : stringList) {
            System.out.println("s = " + s);
        }
        List<String> list = getSendeNumberStrings(stringList);


    }

    public static List<String> getFileLocalName() {
        //获取文件夹中的  所有符合条件文件
        List<String> list = new ArrayList<>();
        File file = new File("C:\\Users\\ASUS\\Desktop\\prm\\新建文件夹");
        File[] files = file.listFiles(new FRFilenameFilerUtill());
        for (File f : files) {
            if (f.isDirectory()) {
            } else {
                list.add(f.getName());
            }
        }
        int i = list == null ? 0 : list.size();

        return list;
    }

    //从文件中读取数据信息
    public static List<String> getSendeNumberStrings(List<String> fileNameLists) {


        List<String> stringList = new ArrayList<>();

        for (String fileNameList : fileNameLists) {
            System.out.println(fileNameList);
        }

        try {
            for (String pathname : fileNameLists) {
                LineIterator iterator = FileUtils.lineIterator(new File("C:\\Users\\ASUS\\Desktop\\prm\\新建文件夹\\" + pathname), "GBK");
                while (iterator.hasNext()) {
                    String line = iterator.nextLine();
                    String s = line.replaceAll("-", "");
                    // System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //同步文件中的信息之后，删除对应路径的文件
        return stringList;

    }

}
