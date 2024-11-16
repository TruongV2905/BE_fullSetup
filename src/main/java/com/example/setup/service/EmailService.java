package com.example.setup.service;

import com.example.setup.model.request.EmailDetail;
import com.example.setup.service.impl.IEmail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmail   {
    @Autowired
    TemplateEngine templateEngine; // tạo ra giao diện
    @Autowired
    JavaMailSender javaMailSender; // đẩy mail đi


    @Override
    public void sendEmail(EmailDetail emailDetail) {
        try {
            Context context = new Context();
            context.setVariable("name", emailDetail.getReceiver().getEmail());
            context.setVariable("button", "Go to my github");
            context.setVariable("link", emailDetail.getLink());
            String template = templateEngine.process("welcome-template", context);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("blearning@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getReceiver().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("ERROR SENT MAIL !!!");
        }
    }
}
