package br.senai.sp.info.pweb.jucacontrol.utils;

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

public class EmailUtils {
	
	/*
	 * Conta que enviará o e-mail
	 */
	public static final String remetente = "senai132.info.2017.1s@gmail.com";
	
	/*
	 * A senha da conta
	 */
	public static final String senhaRemetente = "TecInfoManha2017";
	
	
	private static Session getMailSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //Servidor de envio de e-mails
		props.put("mail.smtp.socketFactory.port", "465"); //Qual é a porta de serviço de envio de email
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true"); //Determina que a autenticação é necessária
		props.put("mail.smtp.port", "465"); //Porta de envio de e-mails
		
		Session session = Session.getInstance(props, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remetente, senhaRemetente);
			}
			
		});
		
		return session;
	}
	
	public static void enviarEmail(String titulo, String corpo, String destinatario) throws AddressException, MessagingException {
		Message msg = new MimeMessage(getMailSession()); //Cria objeto de mensagem com as nossas configs
		
		//Definindo destinatario
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		
		//Definindo o remetente
		msg.setFrom(new InternetAddress(remetente));
		
		//Título
		msg.setSubject(titulo);
		
		//Corpo
		msg.setText(corpo);
		
		//Envia o e-mail...
		Transport.send(msg);
	}
	
	

}
