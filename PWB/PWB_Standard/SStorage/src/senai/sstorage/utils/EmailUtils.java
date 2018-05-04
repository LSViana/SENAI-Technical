package senai.sstorage.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// Email API
public class EmailUtils {

	public static final String EMAIL_ADDRESS = "senai132.info.2017.1s@gmail.com";
	public static final String EMAIL_PASSWORD = "TecInfoManha2017";
	private static Properties props = new Properties();
	private static Session defaultSession;

	{
		// Defining the E-mail Server
		props.put("mail.smtp.host", "smtp.gmail.com");
		// Defining the E-mail Server Port
		props.put("mail.smtp.port", "465");
		// Defining it must authenticate at E-mail Server
		props.put("mail.smtp.auth", "true");
		// Defining the configuration that creates SSL Sockets
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		// Defining the Session
		defaultSession = Session.getDefaultInstance(props, new GmailAuthenticator());		
	}

	// This Session isn't the same from Hibernate, it is the one that keeps the Email Data
	// ALIAS: getMailConfiguration()
	private static Session getSession() {
		return defaultSession;
	}

	private class GmailAuthenticator extends Authenticator {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(EMAIL_ADDRESS, EMAIL_PASSWORD);
		}
	}

	public static void sendEmail(String subject, String body, String addressList) throws AddressException, MessagingException {
		Message msg = new MimeMessage(getSession());
		msg.setFrom(new InternetAddress(EMAIL_ADDRESS));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressList));
		msg.setSubject(subject);
		msg.setText(body);
		// Sending the message through the static method send() from Transport
		Transport.send(msg);
	}

}
