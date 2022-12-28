package com.tjetc.util;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @belongProject:20210601-bysj
 * @belongPackage:com.tjetc.util
 * @author:xujirong
 * @dscription:analysis,解析xml工具类，将文件压缩到指定文件夹中
 * @date:2022-08-06 22:19
 * @version:1.0
 */
public class AnalysisXmlUtils {

    private static String encoding = "\"UTF-8\"";
    private static String version = "\"1.0\"";

    /**
     * @descriprion: 解析xml获取数据集
     * @author: xujirong
     * @date: 2022/8/6 22:23
     * @return: void
     */
    public static void XmlByData() {

        //存储数据集
        Map<String, Object> objectMap = new HashMap<>(16);

        //1.创建SAXReader对象用于读取xml文件
        SAXReader reader = new SAXReader();
        try {
            //2.读取xml文件，获得Document对象
            Document document = reader.read(new File("F:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\resources\\productXml"));
            //3.获取根元素
            Element root = document.getRootElement();
            String nameRoot = root.getName();
            System.out.println("nameRoot = " + nameRoot);
            //4.获取根元素下面的其它子元素
            List<Element> elements = root.elements();
            //存储数据集合
            List<Map<String, Object>> mapList = null;
            for (Element element : elements) {
                mapList = new ArrayList<>();
                System.out.println("----------------");
                System.out.println(element.getName() + " = " + element.getName());
                Iterator iterator = element.elementIterator();
                Map<String, Object> map = new HashMap<>(16);
                while (iterator.hasNext()) {
                    Element element1 = (Element) iterator.next();

                    //存储每个字段中的属性和对应标签值
                    Map<String, Object> mapLabelByAttribute = new HashMap<>();
                    JSONObject jsonObject = new JSONObject();

                    JSONObject attributeLabel = attributeLabel(element1);

                    //根据标签字段属性长度-校验是否给字段补空格-不足时补空格
                    if (!attributeLabel.getString("length").equals(element1.getStringValue().length())) {

                        //int labelLength = Integer.parseInt(attributeLabel.getString("length"));
                        int length = element1.getStringValue().length();
                        String stringValue = element1.getStringValue();

                        stringValue = repairSpace(stringValue, element1.getName(), length);

                        jsonObject.put(element1.getName(), stringValue);
                    } else {
                        jsonObject.put(element1.getName(), element1.getStringValue());
                    }


                    jsonObject.put(element1.getName() + "Attribute", attributeLabel);


                    map.put(element1.getName(), jsonObject);
                }
                mapList.add(map);
                objectMap.put(element.getName(), mapList);
            }
            generateXml(objectMap);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param stringValue:数据值 labelLength：标签长度 length：数据长度
     * @descriprion: 字段长度不足时补空格  补：repair 空格：Space
     * @author: xujirong
     * @date: 2022/8/7 1:34
     * @return:
     */
    public static String repairSpace(String stringValue, String dataId, int length) {

        int labelLength = 0;

        List<Map<String, Object>> mapList = analysisSourceXml();
        for (Map<String, Object> map : mapList) {
            String dataXml = (String) map.get("dataid");
            if (dataId.equals(dataXml)) {
                labelLength = Integer.parseInt((String) map.get("length"));
                break;
            }
        }


        if (labelLength > length) {
            for (int i = 0; i < labelLength - length; i++) {
                if (length != labelLength) {
                    stringValue += " ";
                } else {
                    break;
                }
            }
        }
        return stringValue;
    }


    /**
     * @descriprion: 获取xml标签属性 attribute:属性 Label：标签
     * @author: xujirong
     * @date: 2022/8/6 23:20
     * @return:
     */
    public static JSONObject attributeLabel(Element element) {
        List<Attribute> attributes = element.attributes();
        JSONObject jsonObject = new JSONObject();
        for (Attribute attribute : attributes) {
            //System.out.println(attribute.getName() + " = " + attribute.getValue());
            jsonObject.put(attribute.getName(), attribute.getValue());
        }
        //System.out.println("map = " + map);
        return jsonObject;
    }

    /**
     * @descriprion: 根据数据集生成xml标签格式
     * @author: xujirong
     * @date: 2022/8/6 22:35
     * @return: void
     */
    public static void generateXml(Map<String, Object> objectMap) {


        try {
            File file = new File("D:\\test\\productData_xml.xml");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);

            //其它格式报文--特殊字符~#
            StringBuffer stringBuffer = new StringBuffer();

            //其它格式报文--fix定长报文
            StringBuffer sbFix = new StringBuffer();

            String[] strings = {"sysHeader", "bizHeader", "reqBody"};

            fileWriter.write("---生成xml格式报文---" + "\n");
            fileWriter.write("<?xml version=" + version + " encoding=" + encoding + "?>");
            fileWriter.write("<request>");

            //遍历-获取数据
            for (String string : strings) {

                fileWriter.write("<" + string + ">");
                List<Map<String, Object>> mapList = (List<Map<String, Object>>) objectMap.get(string);
                for (Map<String, Object> map : mapList) {
                    Set<Map.Entry<String, Object>> entries = map.entrySet();
                    for (Map.Entry<String, Object> entry : entries) {
                        fileWriter.write("<" + entry.getKey());
                        Map<String, Object> map1 = (Map<String, Object>) map.get(entry.getKey());

                        JSONObject jsonAttribute = (JSONObject) map1.get(entry.getKey() + "Attribute");

                        stringBuffer.append(map1.get(entry.getKey()) + "~#");
                        sbFix.append(map1.get(entry.getKey()));

                        System.out.println(entry.getKey() + " = " + map1.get(entry.getKey()));
                        Set<Map.Entry<String, Object>> entries1 = jsonAttribute.entrySet();
                        for (Map.Entry<String, Object> stringObjectEntry : entries1) {
                            fileWriter.write(" " + stringObjectEntry.getKey() + "=" + "\"" + stringObjectEntry.getValue() + "\"");
                            System.out.println(stringObjectEntry.getKey() + " = " + stringObjectEntry.getValue());
                        }
                        fileWriter.write(">" + map1.get(entry.getKey()) + "</" + entry.getKey() + ">");
                    }
                }
                fileWriter.write("</" + string + ">");
            }
            fileWriter.write("</request>");

            fileWriter.write("\n");
            fileWriter.write("\n");
            fileWriter.write("---生成~#格式报文---" + "\n");
            fileWriter.write(stringBuffer.toString());

            fileWriter.write("\n");
            fileWriter.write("\n");
            fileWriter.write("---生成fix定长格式报文---" + "\n");
            fileWriter.write(sbFix.toString());

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @descriprion: 解析源接口定义文件  analysis：解析 source：源
     * @author: xujirong
     * @date: 2022/8/7 11:05
     * @return: void
     */
    public static List<Map<String, Object>> analysisSourceXml() {

        List<Map<String, Object>> mapList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try {
            Document read = saxReader.read(new File("F:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\resources\\hxb_productXml.xml"));

            Element rootElement = read.getRootElement();

            List<Element> elementList = rootElement.elements();
            for (Element element : elementList) {
                Map<String, Object> map = new HashMap();
                List<Attribute> attributeList = element.attributes();
                //System.out.println(element.getName() + " = " + element.getName());
                for (Attribute attribute : attributeList) {
                    map.put(attribute.getName(), attribute.getValue());
                }
                mapList.add(map);

            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println("mapList = " + mapList);

        return mapList;
    }

    /**
     * @descriprion: 解析xml字符串，步骤与XmlByData一样
     * @author: xujirong
     * @date: 2022/8/7 13:14
     * @return: void
     */
    public static void jiexiXmlStr() {

        try {
            String strXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><sysHeader><sysHeaderCode length=\"256\" type=\"varchar\">sysHeaderCode123456</sysHeaderCode></sysHeader><bizHeader><bizHeaberCode length=\"256\" type=\"varchar\">bizHeaberCode789123</bizHeaberCode></bizHeader><reqBody><briefly length=\"200\" type=\"varchar\">厂家批发新料一次性加厚朔料袋</briefly><price length=\"10\" type=\"varchar\">100.5</price><photopath length=\"125\" type=\"varchar\">upload/10f83910product-img22.JPG</photopath><name length=\"544\" type=\"varchar\">医用外科口罩独立包装成人一次一片</name><id length=\"256\" type=\"varchar\">672433b7-cb94-40ba-98a6-a048f0124282</id></reqBody></request>";
            Document document = DocumentHelper.parseText(strXml);
            Element rootElement = document.getRootElement();

            //获取根元素下面的其它子元素
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                System.out.println("element.getName() = " + element.getName());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    /**
     * @descriprion: 将定长fix报文解析 ,获取字段值
     * @author: xujirong
     * @date: 2022/8/8 20:54
     * @return: void
     */
    public static void fixByXml() {
        String str = "sysHeaderCode123456厂家批发新料一次性加厚朔料袋100.5upload/10f83910product-img22.JPG医用外科口罩独立包装成人一次一片672433b7-cb94-40ba-98a6-a048f0124282";

        String[] strings = {"19", "14", "5", "32", "16", "36"};

        String[] strData = {"sysHeaderCode", "briefly", "id", "name", "price", "photopath"};

        Map map = new HashMap<String, Object>();

        //上一截取位置--初始位置为0
        int beginIndex = 0;
        //末尾位置
        int stopIndex = 0;

        String sub = "";

        for (int i = 0; i < strings.length; i++) {
            int strInt = Integer.parseInt(strings[i]);

            //拼接字符串截取结束位置下标
            stopIndex += strInt;
            sub = str.substring(beginIndex, stopIndex);
            //System.out.println("sub = " + sub);

            map.put(strData[i], sub);

            //上一次截取完之后，拼接下一次要截取的初始位置下标
            beginIndex += strInt;
        }
        System.out.println("map = " + map);

    }


    public static void main(String[] args) {
        //XmlByData();
        //analysisSourceXml();
        //jiexiXmlStr();
        fixByXml();
    }
}
