package com.parse.email.Controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.parse.email.Model.Email;
import com.parse.email.Model.MailExtract;

@Controller
@RequestMapping(value = "/api")
public class APIController {

	//@Autowired
	//APIService apiService;
	
	@PostMapping(value = "/extractemail")
	public ResponseEntity<Void> doExtractEmail(@Valid @RequestBody Email email, UriComponentsBuilder builder)  {
		System.out.println("Email Address::" + email.getEmailAddress());
		System.out.println("Password::" + "*********");

		try {
			MailExtract.extractEmail(email.getEmailAddress(),email.getPassword());
		} catch (MessagingException e) {
			System.out.println("Messaging Exception has occured");
		} catch (IOException e) {
			System.out.println("IOException has occured");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}
