package com.arbiva.apsfl.mail;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;  
   

@Service("sendingMail")
public class SendingEmail {
    
    
        private MailSender mailSender;  
       
        public void setMailSender(MailSender mailSender) {  
            this.mailSender = mailSender;  
        }  
       
        public String sendMail(String from, String to, String subject, String msg) {  
            //creating message  
        	String status ="success";
        	try{
        	System.out.println(mailSender+"mailSender");
        	
            SimpleMailMessage message = new SimpleMailMessage();  
            //message.setFrom(from);  
            message.setTo(to);  
            message.setSubject(subject);  
            message.setText(msg);  
        	
            //sending message  
            mailSender.send(message); 
        	}
        	catch(Exception e){
        		e.printStackTrace();
        		status="failure";
        		
        	}
        	return status;
        }  
    }  

