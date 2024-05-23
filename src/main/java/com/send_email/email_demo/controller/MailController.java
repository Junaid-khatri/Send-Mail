package com.send_email.email_demo.controller;

import com.send_email.email_demo.dto.MailDto;
import com.send_email.email_demo.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService service;

    @PostMapping("/sendMail")
    public void sendMail(@RequestBody MailDto mail) throws MessagingException {
        service.sendMail(mail);
    }
}
