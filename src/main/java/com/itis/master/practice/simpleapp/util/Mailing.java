package com.itis.master.practice.simpleapp.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*****
 * @author Igor Astafyev
 * June, 2019
 * Sending mail for approvement
 *****/

@Component
public class Mailing {

    private final JavaMailSender mailSender;

    public Mailing(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String email, String name) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom("springbootigoryan@yandex.ru");
            message.setSubject("Подтверждение регистрации");
            String key_one = DigestUtils.md5Hex(email);
            String key_two = DigestUtils.md5Hex(key_one);
            String text = "Здравствуйте, " + name + "! \n\n" +
                    "Для завершения регистрации Вам необходимо перейти по сслыке ниже: \n" +
                    "http://localhost:8080/registration/?mucho=" + key_one + "&rzd=" + key_two + "\n\n" +
                    "Если данное письмо отпрвлено Вам по ошибке, пожалуйста, проигнорируйте его.\n" +
                    "Спасибо!";
            message.setText(text);
            mailSender.send(message);
        });
        service.shutdown();
    }
}