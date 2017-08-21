package com.TM.LTE.userClass;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.TM.LTE.bean.Email;
   
    public class EmailSender  {
         
        @Autowired
        protected JavaMailSender  mailSender;
        
        public void SendEmail(Email email) throws MessagingException, UnsupportedEncodingException {
             
            MimeMessage msg = mailSender.createMimeMessage();
            try {
                msg.setSubject(email.getSubject());
                msg.setText(email.getContent());
                msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(email.getReceiver()));
                msg.setFrom(new InternetAddress("ujin8831@gmail.com", "LTE관리자", "utf-8"));
            }catch(MessagingException e) {
                System.out.println("MessagingException");
                e.printStackTrace();
            }
            try {
            	System.out.println(msg);
            	/*MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
            	helper.setTo(email.getReceiver());
            	helper.setFrom("관리자");
            	helper.setSubject(email.getSubject());
            	helper.setText(email.getContent());*/
                mailSender.send(msg);
            }catch(MailException e) {
                System.out.println("MailException발생");
                e.printStackTrace();
            }
        }
}
