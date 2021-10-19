package com.tjetc.demo.designPattern.demo05;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
public class ReadXML {
    public static Object getObject() {
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo05\\sourceMaterial\\config.xml"));
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = "AbstractFactory." + classNode.getNodeValue();
            System.out.println("新类名：" + cName);
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
