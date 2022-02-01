package com.itis.master.practice.simpleapp.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*****
 * @author Igor Astafyev
 * June 2019
 * Sending mail for approvement
 *****/

@Component
public class Mailing {

	private final JavaMailSenderImpl mailSender;

	public Mailing(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String email, String name) {
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(() -> {
			MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setFrom("springbootigoryan@yandex.ru");
				helper.setTo(email);
				helper.setSubject("Подтверждение регистрации");

				final String key_one = DigestUtils.md5Hex(email);
				final String key_two = DigestUtils.md5Hex(key_one);
				final String text = "<html><body>Здравствуйте, " + name + "! <br/><br/>" +
				                    "Для завершения регистрации Вам необходимо перейти по сслыке ниже: <br/>" +
				                    "<a href=\"http://localhost:8080/registration/?mucho=" + key_one + "&rzd=" + key_two + "\">" +
				                    "Подтвердить регистрацию</a><br/><br/>" +
				                    "Если данное письмо отпрвлено Вам по ошибке, пожалуйста, проигнорируйте его.<br/>" +
				                    "Спасибо! </body></html>";
				helper.setText(text, true);
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
		service.shutdown();
	}
}