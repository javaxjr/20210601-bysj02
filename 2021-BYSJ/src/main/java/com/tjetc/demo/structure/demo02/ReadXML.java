package com.tjetc.demo.structure.demo02;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通过此类去获取.xml文件中的配置信息
public class ReadXML {
    public static List<Map<String,Object>> getObject()
    {
        try
        {
            DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=dFactory.newDocumentBuilder();
            Document doc=builder.parse(new File("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\structure\\demo02\\config.xml"));
            NodeList nl=doc.getElementsByTagName("className");
            Node classNode;
            String cName;
            Class<?> c;
            Object obj;
            Map<String,Object> map;
            List<Map<String,Object>> list = new ArrayList<>();
            for (int i =0;i<nl.getLength();i++){
                map = new HashMap<>();
                classNode = nl.item(i).getFirstChild();
                cName="com.tjetc.demo.structure.demo02."+classNode.getNodeValue();
                c=Class.forName(cName);
                obj=c.newInstance();
                map.put("MotorObject",obj);
                list.add(map);
            }

            return list;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
