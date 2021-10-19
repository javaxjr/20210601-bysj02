package com.tjetc.test.test01;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.*;



public class Email {

    private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);

    private static String defaultSenderName = "1781484543@qq.com";// 默认的发件人用户名，defaultEntity用得到
    private static String defaultSenderPass = "eakkywepkedmehec";// 默认的发件人密码，defaultEntity用得到
    private static String defaultSmtpHost = "";// 默认的邮件服务器地址，defaultEntity用得到

    private String smtpHost="smtp.qq.com"; // 邮件服务器地址
    private Integer port;//邮件服务器端口号
    private String sendUserName; // 发件人的用户名
    private String sendUserPass; // 发件人密码

    private MimeMessage mimeMsg; // 邮件对象
    private Session session;
    private Properties props;
    private Multipart mp;// 附件添加的组件
    private List<FileDataSource> files = new LinkedList<FileDataSource>();// 存放附件文件

    private void init() {
        if (props == null) {
            props = System.getProperties();
        }
        props.setProperty("mail.smtp.host", smtpHost);
        props.setProperty("mail.smtp.auth", "true"); // 需要身份验证
        final String smtpPort = "587";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        session = Session.getInstance(props);
        // 置true可以在控制台（console)上看到发送邮件的过程
        session.setDebug(true);
        // 用session对象来创建并初始化邮件对象
        mimeMsg = new MimeMessage(session);
        // 生成附件组件的实例
        mp = new MimeMultipart();
    }

    private Email(String smtpHost,Integer port, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody,
                  List<String> attachments) {
        this.smtpHost = smtpHost;
        this.sendUserName = sendUserName;
        this.sendUserPass = sendUserPass;
        this.port = port;

        init();
        setFrom(sendUserName);
        setTo(to);
        setCC(cc);
        setBody(mailBody);
        setSubject(mailSubject);
        if (attachments != null) {
            for (String attachment : attachments) {
                addFileAffix(attachment);
            }
        }

    }

    /**
     * 邮件实体
     *
     * @param smtpHost
     * 邮件服务器地址
     * @param sendUserName
     * 发件邮件地址
     * @param sendUserPass
     * 发件邮箱密码
     * @param to
     * 收件人，多个邮箱地址以半角逗号分隔
     * @param cc
     * 抄送，多个邮箱地址以半角逗号分隔
     * @param mailSubject
     * 邮件主题
     * @param mailBody
     *  邮件正文
     * @param attachments
     *  附件路径
     * @return
     */
    public static Email entity(String smtpHost,Integer port, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody,
                               List<String> attachments) {
        return new Email(smtpHost,port, sendUserName, sendUserPass, to, cc, mailSubject, mailBody, attachments);
    }

    /**
     * 默认邮件实体，用了默认的发送帐号和邮件服务器
     *
     * @param to
     *            收件人，多个邮箱地址以半角逗号分隔
     * @param cc
     *            抄送，多个邮箱地址以半角逗号分隔
     * @param subject
     *            邮件主题
     * @param body
     *            邮件正文
     * @param attachments
     *            附件全路径
     * @return
     */
    public static Email defaultEntity(String to, String cc, String subject, String body, List<String> attachments) {
        return new Email(defaultSmtpHost,null, defaultSenderName, defaultSenderPass, to, cc, subject, body, attachments);
    }

    /**
     * 设置邮件主题
     *
     * @param mailSubject
     * @return
     */
    private boolean setSubject(String mailSubject) {
        try {
            mimeMsg.setSubject(mailSubject);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置邮件内容,并设置其为文本格式或HTML文件格式，编码方式为UTF-8
     *
     * @param mailBody
     * @return
     */
    private boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" + mailBody, "text/html;charset=UTF-8");
            // 在组件上添加邮件文本
            mp.addBodyPart(bp);
        } catch (Exception e) {
            System.err.println("设置邮件正文时发生错误！" + e);
            return false;
        }
        return true;
    }

    /**
     * 添加一个附件
     *
     * @param filename
     *            邮件附件的地址，只能是本机地址而不能是网络地址，否则抛出异常
     * @return
     */
    public boolean addFileAffix(String filename) {
        try {
            if (filename != null && filename.length() > 0) {
                BodyPart bp = new MimeBodyPart();
                FileDataSource fileds = new FileDataSource(filename);
                bp.setDataHandler(new DataHandler(fileds));
                bp.setFileName(MimeUtility.encodeText(fileds.getName(), "utf-8", null)); // 解决附件名称乱码
                mp.addBodyPart(bp);// 添加附件
                files.add(fileds);
            }
        } catch (Exception e) {
            System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
            return false;
        }
        return true;
    }

    /**
     * 删除所有附件
     *
     * @return
     */
    public boolean delFileAffix() {
        try {
            FileDataSource fileds = null;
            for (Iterator<FileDataSource> it = files.iterator(); it.hasNext();) {
                fileds = it.next();
                if (fileds != null && fileds.getFile() != null) {
                    fileds.getFile().delete();
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置发件人地址
     *
     * @param from
     *            发件人地址
     * @return
     */
    private boolean setFrom(String from) {
        try {
            mimeMsg.setFrom(new InternetAddress(from));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置收件人地址
     *
     * @param to 收件人的地址
     * @return
     */
    private boolean setTo(String to) {
        if (to == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置抄送
     *
     * @param cc
     * @return
     */
    private boolean setCC(String cc) {
        if (cc == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 发送邮件
     *
     * @return
     */
    public boolean send() throws Exception {
        mimeMsg.setContent(mp);
        mimeMsg.saveChanges();
        System.out.println("正在发送邮件....");
        Transport transport = session.getTransport("smtp");
//         连接邮件服务器并进行身份验证
        transport.connect(smtpHost,port,sendUserName, sendUserPass);
//         发送邮件
        transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
        System.out.println("发送邮件成功！");
        transport.close();
//        Transport.send(mimeMsg);
        System.out.println("发送邮件成功！");
        return true;
    }


    public static String buildContent(List<Map<String,Object>> list) throws IOException {

        //加载邮件html模板
        String fileName = "pod-scale-alarm.html";

        ClassPathResource cpr = new ClassPathResource("path");
        cpr.getFilename();

        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            LOGGER.error("读取文件失败，fileName:{}", fileName, e);
        } finally {
            inputStream.close();
            fileReader.close();
        }


        String contentText = "以下是分销平台产品列表信息, 敬请查看.<br>below is the information of product list on those distribution platforms, please check. ";
        //邮件表格header
        String header = "<td>产品名称</td><td>分销平台名称</td><td>分销平台产品类别</td><td>分销平台产品页URL</td><td>分销平台产品推广URL</td><td>推广url远程地址ip</td><td>分销平台产品页页数</td><td>上架时间</td><td>更新时间</td>";
        StringBuilder linesBuffer = new StringBuilder();
        /*if(list!=null&&list.size()>0) {
            list.forEach(map -> {
                linesBuffer.append("<tr><td>" + map.get("productName") +
                        "</td><td>" + map.get("distributionPlatformName") +
                        "</td><td>" + map.get("productType") +
                        "</td><td>" + map.getOrDefault("distributionPlatformDetailUrl","无") +
                        "</td><td>"+ map.getOrDefault("distributionPlatformProductUrl","无") +
                        "</td><td>"+ map.getOrDefault("addressIp","无") +
                        "</td><td>"+ map.getOrDefault("distributionPlatformPageNum","无") +
                        "</td><td>"+ map.getOrDefault("onShelfTime","无") +
                        "</td><td>" + map.getOrDefault("updateTime","无") + "</td></tr>");
            });
        }*/
        //颜色
        String emailHeadColor = "#fa5834";
        String date = DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss");
        //填充html模板中的五个参数
        String htmlText = MessageFormat.format(buffer.toString(), emailHeadColor, contentText, date, header, linesBuffer.toString());

        //改变表格样式
        htmlText = htmlText.replaceAll("<td>", "<td  style=\"padding:6px 10px; line-height: 150%;border: 1px solid #eee; width: 100px; word-wrap: break-word;\">");
//        htmlText = htmlText.replaceAll("<tr>", "<tr style=\"border-bottom: 1px solid #eee; color:#666;\">");
        return htmlText;
    }


    public static void main(String[] args) throws Exception {
        String userName = "1781484543@qq.com"; // 发件人邮箱
        String password = "nwjvnvkvlikjefdb"; // 发件人密码(不是登录密码是授权码，需要到邮箱里设置开通授权码功能)
        String smtpHost = "smtp.exmail.qq.com"; // 邮件服务器
        Integer port = 465; // 邮件服务器端口号

        String to = "许稷荣"; // 收件人，多个收件人以半角逗号分隔
        String cc = "许稷荣"; // 抄送，多个抄送以半角逗号分隔
        String subject = "尊敬的自己请查看"; // 主题
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i=0;i<2;i++){
            HashMap<String, Object> dateMap = new HashMap<>();
            dateMap.put("productName","魔域");
            dateMap.put("productType","页面网游");
            dateMap.put("distributionPlatformName","网游社区");
            dateMap.put("distributionPlatformDetailUrl","www.baidu.com");
            dateMap.put("addressIp","0.0.0.0");
            dateMap.put("distributionPlatformPageNum","1");
            dateMap.put("onShelfTime","2019-06-28");
            dateMap.put("updateTime","2019-06-28");
            list.add(dateMap);
        }
        String body = Email.buildContent(list); // 正文，可以用html格式的哟
//        List<String> attachments = Arrays.asList("D:\\tmp\\1.png", "D:\\tmp\\2.png"); // 附件的路径，多个附件也不怕

        Email email = Email.entity(smtpHost,port, userName, password, to, cc, subject, body, null);

        System.out.println("body = " + body);

        email.send(); // 发送！
    }
}

