package com.itis.master.practice.simpleapp.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/*****
 * @author Igor Astafyev
 * June, 2019
 * Sending mail for approvement
 *****/

@Component
public class Mailing {

    private static JavaMailSender mailSender;

    @Autowired
    public Mailing(JavaMailSender mailSender) {
        Mailing.mailSender = mailSender;
    }

    public static void sendMail(String email, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("springbootigoryan@yandex.ru");
        message.setSubject("Подтверждение регистрации");
        String key_one = DigestUtils.md5Hex(email);
        String key_two = DigestUtils.md5Hex(key_one);
        String text = "Привет, " + name + "! \n\n" +
                "Для завершения регистрации Вам необходимо перейти по сслыке ниже: \n" +
                "http://localhost:8080/registration/?mucho=" + key_one + "&rzd=" + key_two + "\n\n" +
                "Если данное письмо отпрвлено Вам по ошибке, пожалуйста, проигнорируйте его.\n" +
                "Спасибо!";
        message.setText(text);
        mailSender.send(message);
    }
}
