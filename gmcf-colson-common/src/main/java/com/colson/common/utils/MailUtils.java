package com.colson.common.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    public static void main(String[] args) {
        // SMTP服务器配置
        String host = "smtp.qiye.aliyun.com"; // 阿里云邮箱的SMTP服务器地址
        String port = "465"; // SMTP服务器端口（SSL）
        String username = "system@ronjusk.com"; // 你的阿里云邮箱地址
        String password = "FVjTKkehGyZbdtFu"; // 阿里云邮箱密码或授权码

        // 收件人信息
        String recipient = "670317458@qq.com"; // 收件人邮箱地址
        String subject = "测试邮件"; // 邮件主题
        String body = "这是一封来自阿里云邮箱的测试邮件。"; // 邮件内容

        // 配置邮件会话属性
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.checkserveridentity", "true");

        // 创建会话对象
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建邮件对象
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // 发件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // 收件人地址
            message.setSubject(subject); // 邮件主题
            message.setText(body); // 邮件内容

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("邮件发送失败：" + e.getMessage());
        }
    }
}

