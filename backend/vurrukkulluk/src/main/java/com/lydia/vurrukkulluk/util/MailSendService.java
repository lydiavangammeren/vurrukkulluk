package com.lydia.vurrukkulluk.util;

import com.lydia.vurrukkulluk.config.MailConfig;
import com.lydia.vurrukkulluk.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailSendService {

    private final MailConfig mailConfig;
    public boolean sendOTPMail(String OTP, User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@vurrukkulluk.com");
            message.setTo(user.getEmail());
            message.setSubject("Vurrukkulluk OTP");
            message.setText(String.format("Dit is uw one time password:\n\n%s\n\nDeze is geldig voor 5 minuten.",OTP));
            JavaMailSender emailSender = getJavaMailSender();
            System.out.println(emailSender.toString());
            emailSender.send(message);
            return true;
        } catch (MailException e) {
            System.out.println("-----no-mail-server-or-errors-----");
            return false;
        }
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(mailConfig.getPort());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
