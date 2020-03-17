package com.zlx.bpms.utils;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Package: com.zlx.bpms.utils
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:邮件发送工具
 */
public class EmailSendTools {


    /**
     * 发送邮件
     *
     * @param content 邮件内容
     * @param email   邮箱
     * @param subject 主题
     */
    public static void sendEmail(String content, String email, String subject) throws MessagingException {
        // 创建连接对象
        Properties props = new Properties();
        // 邮件发送的协议
        props.put("mail.transport.protocol", "smtp");
        //发送邮件的服务器
        props.put("mail.smtp.host", "smtp.163.com");
        //开启授权
        props.put("mail.smtp.auth", "true");
        // 发送邮件具体信息及端口号
        //发送邮件的消息工厂
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 是否具有消息工厂回调
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        // 端口号默认是以25端口发送，但阿里云已经关闭了25端口
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        //认证信息
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("favor_zlx@163.com", "zlx9420");
            }
        });

        // 创建邮件对象
        Message message = new MimeMessage(session);
        //发件人
        message.setFrom(new InternetAddress("favor_zlx@163.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 抄送者 ,如果没有这个,网易邮箱会认为是一条垃圾邮件,而导致发送失败
        message.setRecipient(Message.RecipientType.CC, new InternetAddress("favor_zlx@163.com"));
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setContent(content,"text/html;charset=utf-8");
        //发送邮件
        Transport.send(message);
    }


}
