package com.example.mail.service;

import com.example.common.domain.mail.SimpleMail;

import java.io.File;

public interface MailService {
    public void sendSimpleMail(SimpleMail simpleMail);

    public void sendAttachFileMail(String from, String to, String subject, String content, File file);
}
