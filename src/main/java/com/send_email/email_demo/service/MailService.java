package com.send_email.email_demo.service;

import com.send_email.email_demo.dto.MailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine engine;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendMail(MailDto mail) throws MessagingException {

        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMailMessage);

        mailHelper.setTo(mail.getToMail());
        mailHelper.setSubject(mail.getSubject());
        mailHelper.setFrom(from);
        System.out.println(mail.isHTML());
        if(mail.isHTML()){

            Context context = new Context();

            context.setVariable("content", mail.getMessage());
            String processedMessage = engine.process("emailTemp",context);
            mailHelper.setText(processedMessage,true);
        }
        else{
            mailHelper.setText(mail.getMessage(),false);
        }
        mailSender.send(mimeMailMessage);

    }
}
