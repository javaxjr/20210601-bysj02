package com.tjetc.demo.csvDemo;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CSV {

    public static void main(String[] args) {

        SendeNumber s1 = new SendeNumber("1000", "202207", "100", "0", "0", "√", "已同步");
        SendeNumber s2 = new SendeNumber("1040", "202427", "100", "0", "0", "x", "码号不存在");
        SendeNumber s3 = new SendeNumber("1022", "224207", "100", "0", "0", "x", "码号不存在");
        SendeNumber s4 = new SendeNumber("1020", "224207", "100", "0", "0", "√", "已同步");

        List<SendeNumber> list = new ArrayList<>();
        list.add(s1);


        String fileName = "sendnumber_202106_0.csv";


        String s = fileName.substring(0, 14);
        System.out.println("s = " + s);


        String concat = fileName.concat(".csv");
        System.out.println("concat = " + concat);

        String[] split = fileName.split(".csv");

        for (String s5 : split) {
            s5.toLowerCase().endsWith(".csv");
            System.out.println("split0 = " + s5.toLowerCase().endsWith(".csv"));
        }


        boolean flag = writeToTxt(list);
        //boolean Flag=createCsvFile(list,"G:\\CSVDir","csvFile");
        if (flag == true) {
            System.out.print("CSV文件创建成功！");
        } else {
            System.out.print("CSV文件创建失败！");
        }
    }

    public static boolean createCsvFile(List<Object> list, String filePath, String fileName) {
        //标记文件生成是否成功；
        boolean flag = true;

        //文件输出流
        BufferedWriter fileOutputStream = null;

        try {
            //含文件名的全路径
            String fullPath = "c:/opt/prm_pd/ftp/nms/local/sendenumber.csv";
            File file = new File(fullPath);
            if (!file.getParentFile().exists())     //如果父目录不存在，创建父目录
            {
                file.getParentFile().mkdirs();
            }
            if (file.exists())     //如果该文件已经存在，删除旧文件
            {
                file.delete();
            }
            file = new File(fullPath);
            file.createNewFile();

            //格式化浮点数据
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(10);     //设置最大小数位为10；

            //格式化日期数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            //实例化文件输出流
            fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GB2312"), 1024);

            //遍历输出每行
            Iterator<Object> ite = list.iterator();

            while (ite.hasNext()) {
                Object[] rowData = (Object[]) ite.next();
                for (int i = 0; i < list.size(); i++) {
                    Object obj = rowData[i];   //当前字段
                    //格式化数据
                    String field = "";
                    if (null != obj) {
                        if (obj.getClass() == String.class)     //如果是字符串
                        {
                            field = (String) obj;
                        } else if (obj.getClass() == Double.class || obj.getClass() == Float.class)   //如果是浮点型
                        {
                            field = formatter.format(obj);   //格式化浮点数，使浮点数不以科学计数法输出
                        } else if (obj.getClass() == Integer.class || obj.getClass() == Long.class | obj.getClass() == Short.class || obj.getClass() == Byte.class) {
                            //如果是整型
                            field += obj;
                        } else if (obj.getClass() == Date.class)   //如果是日期类型
                        {
                            field = sdf.format(obj);
                        } else {
                            field = " ";   //null时给一个空格占位
                        }
                        //拼接所有字段为一行数据
                        if (i < rowData.length - 1)     //不是最后一个元素
                        {
                            System.out.println("\"" + field + "\"" + ",");
                            fileOutputStream.write("\"" + field + "\"" + ",");
                        } else {
                            //最后一个元素
                            fileOutputStream.write("\"" + field + "\"");
                            System.out.println("\"" + field + "\"");
                        }
                    }
                    /*//创建一个新行
                    if (ite.hasNext())
                    {
                        //fileOutputStream.newLine();
                    }*/
                }
                fileOutputStream.newLine();     //换行，创建一个新行；
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static boolean writeToTxt(List<SendeNumber> list) {
        BufferedWriter bufferedWriter = null;
        boolean flag = false;
        try {

            //含文件名的全路径
            String fullPath = "D:/upload/sendenumber.csv";
            File file = new File(fullPath);
            if (!file.getParentFile().exists())     //如果父目录不存在，创建父目录
            {
                file.getParentFile().mkdirs();
            }
            if (file.exists())     //如果该文件已经存在，删除旧文件
            {
                file.delete();
            }
            file = new File(fullPath);

            file.createNewFile();

            //实例化文件输出流
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GB2312"), 1024);

            int sageCoun = 0;
            int bageCoun = 0;
            bufferedWriter.write("\"" + "省分" + "\"" + "," + "\"" + "地市" + "\"" + "," + "\"" + "触点来源" + "\"" + "," + "\"" + "场景类型" + "\"" + "," + "\"" + "属性编码1" + "\"" + "," + "\"" + "属性值1" + "\"" + "," + "\"" + "属性编码2" + "\"" + "," + "\"" + "属性值2" + "\"" + "," + "\"" + "工作组ID" + "\"" + "," + "\"" + "工作组名称" + "\"" + "," + "\"" + "派单规则" + "\"" + "," + "\"" + "是否有效" + "\"" + "," + "\"" + "自定义规则属性" + "\r");
            /*for(int i = 0; i < list.size(); i++ )
            {
                if ("√".equals(list.get(i).getResult())){
                    sageCoun++;
                }else {
                    bageCoun++;
                }
                bufferedWriter.write("\""+list.get(i).getCode()+"\""+","+"\""+list.get(i).getPeriod()+"\""+","+"\""+list.get(i).getResourceUsedNum()+"\""+","+"\""+list.get(i).getBreachUsedNum()+"\""+","+"\""+list.get(i).getType()+"\""+","+"\""+list.get(i).getResult()+"\""+","+"\""+list.get(i).getReason()+"\""+",");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("\r"+"\""+"总记录数:"+""+"\""+","+"\""+list.size()+"\""+","+"\""+"已同步数:"+"\""+","+"\""+sageCoun+"\""+","+"\""+"未同步数:"+"\""+","+"\""+bageCoun+"\"");
            flag=true;*/
        } catch (Exception e) {

            flag = false;
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }
}
