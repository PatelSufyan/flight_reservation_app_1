package com.flight_reservation_app_1.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress, String filePath) {
		
		MimeMessage message = sender.createMimeMessage();
		
		try {
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject("Itinerary of Flight");
			messageHelper.setText("Please find the attached file, this contains sensitive information please do not share it. Have a pleasant flgiht");
			messageHelper.addAttachment("Itinerary", new File(filePath));
			sender.send(message);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
