package com.example.common.domain.mail;

import java.io.Serializable;

/**
 * 简单的邮件
* */
public class SimpleMail implements Serializable {
    private static final long serialVersionUID = 875480465882958390L;
    /**
    * 邮件发送者
    * */
    private String from;
    /**
    * 邮件接收者
    * */
    private String to;
    /**
    * 抄送人
    * */
    private String cc;
    /**
    * 邮件主题
    * */
    private String subject;
    /**
    * 邮件内容
    * */
    private String content;

    public SimpleMail(String to,String subject,String content){
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    /**
     * 生成验证码邮件实体类
     * @param to 邮件接收者
     * @param verificationCode 验证码
     * @return SimpleMail 邮件实体类
    * */
    public static SimpleMail generateVerificationCodeMail(String to,String verificationCode){
        return new SimpleMail(to,"邮箱绑定信息","验证码为："+verificationCode+"\n验证码区分大小写 60秒内有效");
    }
    public static SimpleMail generateWarningMail(String to,String warningInfo){
        return new SimpleMail(to,"警报信息",warningInfo);
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
