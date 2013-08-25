/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclinic.database;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohan
 */
public class Database {
	private String URL = "jdbc:mysql://localhost:3306/myclinic?user=root&password=admin";
	private static Logger logger = Logger.getLogger("database.Database");
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	 
                    
    public String getDBURL(){
        return URL;
    }
    
    public void sendSSLMessage(String[] recipients, String subject, String message, String from) 
			throws MessagingException {
		boolean debug = true;
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {

		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("rock21586@gmail.com", "password");
		}
		});

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
		addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
    
    public static void main(String[] args) throws SecurityException, IOException{
    	FileHandler fh = new FileHandler("E:\\Tomcat\\apache-tomcat-7.0.8\\logs\\myclinic.log");
    	Connection conn = null;
    	try{
    		logger.addHandler(fh);
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        
	        Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//			String dateNow = formatter.format(currentDate.getTime());
			String dateNow = "2011-09-16";
			java.util.Date sysDate = formatter.parse(dateNow);
			System.out.println("after parsing date "+ sysDate);
			java.sql.Date sqlDate = new java.sql.Date(sysDate.getTime());
			System.out.println("sysdate.gettime() = "+sysDate.getTime());
			System.out.println("the Sql Date is "+sqlDate);
	        
    	}catch(Exception e){
    		System.out.println(e);
    		logger.log(Level.WARNING, "Something wrong Happened", e);
    	}
    }
}

