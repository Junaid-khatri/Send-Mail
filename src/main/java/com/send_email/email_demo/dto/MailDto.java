package com.send_email.email_demo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailDto {

    String toMail;
    String subject;
    String message;
    @JsonAlias(value = "html")
    boolean isHTML;

}
