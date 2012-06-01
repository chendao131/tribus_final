package util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMail {

	public static void main(String[] args) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "nihao test";

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("admin@example.com",
					"Example.com Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"fuzantott@163.com", "Mr. User"));
			msg.setSubject("Your Example.com account has been activated");
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
