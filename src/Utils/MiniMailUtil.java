package Utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MiniMailUtil {
	private static final String MINI_EMAIL_ADDRESS = "Mini_Manage@163.com";
	private static final String MINI_EMAIL_POSSWORD = "a66210453";
	private static final String MINI_EMAIL_HOST = "smtp.163.com";
	private static MiniMailUtil mini = new MiniMailUtil();
	private static Properties pros = new Properties();
	private static Session s ;
	private static InternetAddress from = new InternetAddress();

	private MiniMailUtil() {}

	public static MiniMailUtil getInstance() {
		return mini;
	}
	static {
		pros.put("mail.smtp.host", MINI_EMAIL_HOST);
		pros.put("mail.smtp.auth", "true");
		s= Session.getInstance(pros);
		s.setDebug(true);
		from.setAddress(MINI_EMAIL_ADDRESS);
	}

	public void sendMail(String title, String content, String sendEmail)
			throws MessagingException {
		MimeMessage message = new MimeMessage(s);
		message.setFrom(from);
		InternetAddress to = new InternetAddress(sendEmail);
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject(title);
		message.setSentDate(new Date());
		BodyPart mdp = new MimeBodyPart();
		mdp.setContent(content, "text/html;charset=UTF-8");
		Multipart mm = new MimeMultipart();
		mm.addBodyPart(mdp);
		message.setContent(mm);
		message.saveChanges();
		Transport transport = s.getTransport("smtp");
		transport.connect(MINI_EMAIL_HOST, MINI_EMAIL_ADDRESS,
				MINI_EMAIL_POSSWORD);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}
