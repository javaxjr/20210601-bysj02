package com.tjetc.demo.structure.demo03;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class ReadXML {

    public static Object getObject(String args){

        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\structure\\demo03\\config.xml"));
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = null;
            if (args.equals("color")) {
                classNode = nl.item(0).getFirstChild();
            } else if (args.equals("bag")) {
                classNode = nl.item(1).getFirstChild();
            }
            String cName = "com.tjetc.demo.structure.demo03." + classNode.getNodeValue();
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
