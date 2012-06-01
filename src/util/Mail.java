package util;

import java.util.Date;  
import java.util.Properties;  
import javax.mail.Address;  
import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class Mail{  
      
    private String host = "smtp.163.com";  
    
    private String username = "fuzantott@163.com";  
      
    private String password = "ilovexy19841022";  
      
    private String mail_head_name = "this is head of this mail";  
  
    private String mail_head_value = "this is head of this mail";  
  
    private String mail_to = "zfu7@hawk.iit.edu";  
  
    private String mail_from = "fuzantott@163.com";  
  
    private String mail_subject = "this is the subject of this test mail";  
  
    private String mail_body = "this is the mail_body of this test mail";  
  
    private String personalName = "Tribus Company LLC";  
  
      
  
    /** 
     * 
     */  
    public void send() throws Exception{  
        try{  
            Properties props=new Properties();  
            Authenticator auth = new Email_Autherticator(); // 
            props.put("mail.smtp.host", host);  
            props.put("mail.smtp.auth", "true");  
            Session session = Session.getDefaultInstance(props, auth);  
    
            //
            MimeMessage message = new MimeMessage(session);  
            
            //message.setContent("foobar, "application/x-foobar"); //  
            message.setSubject(mail_subject); //
            message.setText(mail_body); //  
            message.setHeader(mail_head_name, mail_head_value); //  
              
            message.setSentDate(new Date()); //
            Address address = new InternetAddress(mail_from, personalName);  
            message.setFrom(address); //  
            Address toAddress = new InternetAddress(mail_to); //  
            message.addRecipient(Message.RecipientType.TO, toAddress);  
            Transport.send(message); //
            //System.out.println("send success!");  
        }catch (Exception ex){  
            ex.printStackTrace();  
            throw new Exception(ex.getMessage());  
        }  
    }  
  
    /** 
     * 
     */  
    public class Email_Autherticator extends Authenticator{  
        public Email_Autherticator(){  
            super();  
        }  
  
        public Email_Autherticator(String user, String pwd){  
            super();  
            username = user;  
            password = pwd;  
        }  
  
        public PasswordAuthentication getPasswordAuthentication(){  
            return new PasswordAuthentication(username, password);  
        }  
    }

	public String getMail_head_name() {
		return mail_head_name;
	}

	public void setMail_head_name(String mail_head_name) {
		this.mail_head_name = mail_head_name;
	}

	public String getMail_head_value() {
		return mail_head_value;
	}

	public void setMail_head_value(String mail_head_value) {
		this.mail_head_value = mail_head_value;
	}

	public String getMail_to() {
		return mail_to;
	}

	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

	public String getMail_from() {
		return mail_from;
	}

	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}

	public String getMail_subject() {
		return mail_subject;
	}

	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}

	public String getMail_body() {
		return mail_body;
	}

	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}  
  
//    public static void main(String[] args){  
//        Mail sendmail = new Mail();  
//        try{  
//            sendmail.send();  
//        }catch (Exception ex){  
//            ex.printStackTrace();  
//        }  
//    }             
}  