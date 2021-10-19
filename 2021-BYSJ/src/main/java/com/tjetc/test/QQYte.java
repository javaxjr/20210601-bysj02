package com.tjetc.test;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;

import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;


public class QQYte {
    public static void main(String[] args) {
        // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱端口587
        props.put("mail.smtp.port", "587");
        // 此处填写，写信人的账号
        props.put("mail.user", "1781484543@qq.com");
        // 此处填写16位STMP口令
        props.put("mail.password", "nwjvnvkvlikjefdb");


        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            /*protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }*/
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        try {
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);

            // 设置收件人的邮箱
            //InternetAddress to = new InternetAddress("xujr3@asiainfo.com"); 有多少个邮箱，发送给多少人
            //一般数组是不能够添加元素的  刚开始初始化的时候数组就已经规定好了长度
            //String[] strings = new String[]{"1781484543@qq.com","2385824854@qq.com","2498068709@qq.com","xujr3@asiainfo.com"};

            //使用list集合代替 数组方便又快捷   ArrayList本身是个数组（底层是用数组实现的）
            List<String> stringList = new ArrayList<>();
            stringList.add("1781484543@qq.com");
            //stringList.add("2385824854@qq.com");
            //stringList.add("2498068709@qq.com");
            stringList.add("xujr3@asiainfo.com");

            for (String s : stringList) {
                System.out.println("s = " + s);

                InternetAddress to2 = new InternetAddress(s);
                //message.setRecipient(RecipientType.TO, to);
                message.setRecipient(RecipientType.TO,to2);
                //使用UUID自动生成6位验证码
                String substring = UUID.randomUUID().toString();
                // 设置邮件标题
                message.setSubject("您的验证码信息如下");

                // 设置邮件的内容体
                message.setContent(substring, "text/html;charset=UTF-8");

                // 设置邮件消息内容、包含附件
                Multipart msgPart = new MimeMultipart();
                message.setContent(msgPart);

                MimeBodyPart body = new MimeBodyPart();//正文
                //MimeBodyPart attach = new MimeBodyPart();//附件
                msgPart.addBodyPart(body);
                //msgPart.addBodyPart(attach);

                //设置正文内容
                String uuid=UUID.randomUUID().toString();
                //body.setContent("记得观看哟，更多精彩视频请关注抖音：20762851861","text/html;charset=utf-8");
                body.setContent(uuid,"text/html;charset=utf-8");
                //设置附件内容
                //attach.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\17814\\Desktop\\商品表信息.xls")));
                //attach.setFileName((MimeUtility.encodeText("旅行视频合集.mp4")));

                message.saveChanges();
                //邮件的创建时间
               // message.setSentDate(new Date());

                // 最后当然就是发送邮件啦
                Transport.send(message);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
