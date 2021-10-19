package com.tjetc.test;

import com.sun.mail.util.MailSSLSocketFactory;
import com.sun.mail.util.MimeUtil;
import com.tjetc.domain.Product;
import com.tjetc.domain.WarnMesssgeData;
import com.tjetc.service.ProductService;
import com.tjetc.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class QQYteExcel {


    public static void sendInforExcel(){

        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"dd",3.5,10,"dd","dd","dd","dd"));
        list.add(new Product(2,"dd",3.5,10,"455","545","77","775"));
        list.add(new Product(3,"dd",3.5,10,"tw","特热热","儿童团特","儿童热"));

        //创建商品文件
        String fileName = "商品表信息.xls";
        //文件存储在本机的路径
        String fileFullPath = "c:/Users/17814/Desktop/"+fileName;


        if (list.size()>0){
            try {
                try {

                    //生成Excel文件
                    OutputStream out = null;
                    File file = new File(fileFullPath);
                    if (!file.getParentFile().exists()){
                        file.getParentFile().mkdirs();
                    }

                    if (!file.exists()){
                        file.createNewFile();
                    }

                    out = new FileOutputStream(file);

                    //商品信息  标题名称
                    String[] fields = {"编号","单价","数量","图片"};
                    //SXSSFWorkbook wb = exportInfoExcel(WorkInfos, null, 0, fields, nowTime); //第1页查询结束（第一次查询）

                    HSSFWorkbook wb = ExcelUtils.productExcel(list,fields);
                    wb.write(out);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                WarnMesssgeData warnMes = new WarnMesssgeData();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:SS:DD");
                Date date = new Date();
                sf.format(date);

                warnMes.setSubject("商品信息表");
                warnMes.setContent("商品信息见附件，时间："+sf);//邮件内容
                warnMes.setFileName(fileName);
                warnMes.setAddress(fileFullPath);

                //发送邮件   重写一个方法用于描述发送细节
                QQYteExcel.sendExcelMail(warnMes);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("发送异常。。。。");
            }


        }

    }

    public static void sendExcelMail(WarnMesssgeData warnmes){

        try {
            Properties prop = new Properties();
            //开启debug调试，以便在控制台查看
            prop.setProperty("mail.debug","true");
            // 设置邮件服务器主机名
            prop.setProperty("mail.host", "smtp.exmail.qq.com");
            // 发送服务器需要身份验证
            prop.setProperty("mail.smtp.auth", "true");
            // 发送邮件协议名称
            prop.setProperty("mail.transport.protocol", "smtp");
            //端口
            prop.setProperty("mail.smtp.port", "465");

            // 开启SSL加密，否则会失败
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            //创建session
            Session session = Session.getInstance(prop);
            //通过Session得到transport对象
            Transport ts = session.getTransport();
            //连接邮件服务器 ：邮箱类型 账号，授权码代替密码（安全性）
            ts.connect("smtp.exmail.qq.com","1781484543@qq.com","eakkywepkedmehec");

            //创建邮件
            InternetAddress[] reveiverArr = {new InternetAddress("1781484543@qq.com"),new InternetAddress("xujr3@asiainfo.com")};
            Message message = createExcelMail(session,reveiverArr,warnmes);

            ts.sendMessage(message,message.getAllRecipients());

            ts.close();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Message createExcelMail(Session session, InternetAddress[] reveiverArr, WarnMesssgeData warnmes) {


        try {
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //指明邮件的发送人
            message.setFrom(new InternetAddress("1781484543@qq.com"));
            //指明邮件的收件人 ，现在发件人和收件人是一样的
            message.setRecipients(Message.RecipientType.TO,reveiverArr);
            //邮件标题
            message.setSubject(warnmes.getSubject());

            // 设置邮件消息内容、包含附件
            Multipart msgPart = new MimeMultipart();
            message.setContent(msgPart);

            MimeBodyPart body = new MimeBodyPart();//正文
            MimeBodyPart attach = new MimeBodyPart();//附件
            msgPart.addBodyPart(body);
            msgPart.addBodyPart(attach);

            //设置正文内容
            body.setContent(warnmes.getContent(),"text/html;charset=utf-8");
            //设置附件内容
            attach.setDataHandler(new DataHandler(new FileDataSource(warnmes.getAddress())));
            attach.setFileName((MimeUtility.encodeText(warnmes.getFileName())));

            message.saveChanges();
            //邮件的创建时间
            message.setSentDate(new Date());
            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        QQYteExcel.sendInforExcel();
    }
}
