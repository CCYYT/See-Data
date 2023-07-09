package com.example.mail.service.Impl;

import com.example.common.domain.mail.SimpleMail;
import com.example.mail.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service("MailService")
public class MailServiceImpl implements MailService {
    // JavaMailSender 在Mail 自动配置类 MailSenderAutoConfiguration 中已经导入，这里直接注入使用即可
    @Resource
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromName;

    //方法5个参数分别表示：邮件发送者、收件人、抄送人、邮件主题以及邮件内容
    public void sendSimpleMail(SimpleMail simpleMail) {
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        simpMsg.setFrom(fromName);
        simpMsg.setTo(simpleMail.getTo());
        simpMsg.setCc(fromName);
        simpMsg.setSubject(simpleMail.getSubject());
        simpMsg.setText(simpleMail.getContent());
        javaMailSender.send(simpMsg);
    }


    // 发送带附件的邮件
    // 方法5个参数分别表示：邮件发送者、收件人、邮件主题、邮件内容、以及附件
    public void sendAttachFileMail(String from, String to,
                                   String subject, String content, File file) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            // 这里使用 MimeMessageHelper 简化了邮件配置
            // 第二个参数true表示构造一个 multipart message 类型邮件
            // multipart message类型邮件包含多个正文、附件以及内嵌资源，邮件表现形式更加丰富
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            // 最后通过 addAttachment 方法添加附件
            helper.addAttachment(file.getName(), file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


