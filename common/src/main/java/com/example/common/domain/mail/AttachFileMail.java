package com.example.common.domain.mail;

import java.io.File;

/**
* 带附件的邮件
* */
public class AttachFileMail {
    //方法5个参数分别表示：邮件发送者、收件人、邮件主题、邮件内容、以及附件
    String from;
    String to;
    String subject;
    String content;
    File file;
}
