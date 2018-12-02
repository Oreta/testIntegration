package service;

import java.util.Properties;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

public class EmailService {
	Mailer mailerBuilder;

	public EmailService() {
		super();

		mailerBuilder = MailerBuilder.withSMTPServer("smtp.gmail.com", 587, "ausecourse@gmail.com", "ausecourse123")
				.withTransportStrategy(TransportStrategy.SMTP_TLS).buildMailer();
		System.out.println("OK");
	}
	// CARE whithout proxy
	public void sendMail(String toName, String toMail,String subject, String text) {
		Email email = EmailBuilder.startingBlank().from("Ludovic Ouvry", "ausecourse@gmail.com")
				.to(toName, toMail).withSubject(subject).withPlainText(text).buildEmail();
		mailerBuilder.sendMail(email);
	}
}