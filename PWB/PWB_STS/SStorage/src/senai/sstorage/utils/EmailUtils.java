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

	public static final String EMAIL_ADDRESS = "sstorageteam@gmail.com";
	public static final String EMAIL_PASSWORD = "ac1ce3ss2";
	private static Properties props = new Properties();
	private static Session defaultSession;

	private static void initializeSession() {
		// Defining the E-mail Server
		props.put("mail.smtp.host", "smtp.gmail.com");
		// Defining the E-mail Server Port
		props.put("mail.smtp.port", "465");
		// Defining it must authenticate at E-mail Server
		props.put("mail.smtp.auth", "true");
		// Defining the configuration that creates SSL Sockets
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		// Enabling SSL
		props.put("mail.smtp.EnableSSL.enable", "true");
		// Defining the Session
		defaultSession = Session.getDefaultInstance(props, new GmailAuthenticator(EMAIL_ADDRESS, EMAIL_PASSWORD));
	}

	// This Session isn't the same from Hibernate, it is the one that keeps the
	// Email Data
	// ALIAS: getMailConfiguration()
	private static Session getSession() {
		return defaultSession;
	}

	public static void sendEmail(String subject, String body, String addressList)
			throws AddressException, MessagingException {
		if (getSession() == null) {
			initializeSession();
		}
		Message msg = new MimeMessage(getSession());
		msg.setFrom(new InternetAddress(EMAIL_ADDRESS));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressList));
		msg.setSubject(subject);
		msg.setText(body);
		// Sending the message through the static method send() from Transport
		Transport.send(msg);
	}

}

class GmailAuthenticator extends Authenticator {
	
	private String EMAIL_ADDRESS;
	private String EMAIL_PASSWORD;

	public GmailAuthenticator() {
		// TODO Auto-generated constructor stub
	}
	
	public GmailAuthenticator(String EMAIL_ADDRESS, String EMAIL_PASSWORD) {
		super();
		this.EMAIL_ADDRESS = EMAIL_ADDRESS;
		this.EMAIL_PASSWORD = EMAIL_PASSWORD;
	}



	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(EMAIL_ADDRESS, EMAIL_PASSWORD);
	}
}
