package com.tjetc.demo.designPattern.demo04;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.*;

//source material:素材
public class RedXML1 {

    /*
    * 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    * */
    public static Object getObject(){
        try {
            //创建文档对象
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = df.newDocumentBuilder();
            //填写xml文件的路径
            Document doc;
            doc = builder.parse(new File("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo04\\sourceMaterial\\config1.xml"));

            NodeList n1 = doc.getElementsByTagName("className");
            Node node = n1.item(0).getFirstChild();

            String cName = "demo04."+node.getNodeValue();

            System.out.println("cName = " + cName);
            //system.out.pringtln(新类名：+CName);
            //通过类名生成实例对象并将其返回
            /*Class<?> c =  Class.forName(cName);*/
            AbstractFactoryTest.AbstractFactory  ob = new AbstractFactoryTest.ConcreteFactory2();

            Object obj = ob;

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
