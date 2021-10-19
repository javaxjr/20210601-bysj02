package com.tjetc.demo.structure.demo02;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/*
* 适配器模式
* */

public class MotorAdapterTest {
    public static void main(String[] args) {

        List<Map<String, Object>> list = ReadXML.getObject();
        for (Map<String, Object> map : list) {
            Object object = map.get("MotorObject");
            Motor motor = (Motor) object;
            motor.drive();
        }
    }


}
