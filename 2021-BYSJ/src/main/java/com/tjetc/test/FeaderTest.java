package com.tjetc.test;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FeaderTest {
    public static void main(String[] args) {

        try {
            DataInputStream in = new DataInputStream(new FileInputStream(new File("c:/opt/prm_pd/ftp/nms/local/sendnumber_202107_0.csv")));
            BufferedReader br= new BufferedReader(new InputStreamReader(in,"utf-8"));//这里如果csv文件编码格式是utf-8,改成utf-8即可

            char[] buf = new char[10240];

            while ((br.read(buf))!=-1){

                String s = new String(buf);
                String[] split = s.split("\r\n");

                System.out.println(split[0]);
                if (split.length>2){
                    for (int i = 2;i<split.length;i++) {

                        if (split[i].trim().length()==0){
                            continue;
                        }
                        System.out.println(split[i]);
                    }
                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<String[]> csv(String fileName) {
        List<String[]> csvList =new ArrayList<String[]>();

            try {
                InputStreamReader is =new InputStreamReader(new  FileInputStream(fileName),"gbk");
                CSVParser csvParser =new CSVParserBuilder().build();
                CSVReader reader =new CSVReaderBuilder(is).withCSVParser(csvParser).build();
                csvList = reader.readAll();
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

        return csvList;
    }

}
