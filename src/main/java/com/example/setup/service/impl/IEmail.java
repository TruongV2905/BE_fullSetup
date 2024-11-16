package com.example.setup.service.impl;

import com.example.setup.model.request.EmailDetail;
import org.springframework.context.annotation.Bean;

public interface IEmail {
    public void sendEmail(EmailDetail emailDetail);

}
