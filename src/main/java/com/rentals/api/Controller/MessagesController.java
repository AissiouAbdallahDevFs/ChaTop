package com.rentals.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rentals.api.Service.MessagesService;
import com.rentals.api.model.Messages;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class MessagesController {
	
	@Autowired
	private MessagesService messagesService;
	
	@GetMapping("Messages")
	public ResponseEntity<Iterable<Messages>> getMessages(){
		Iterable<Messages> messages = messagesService.getMessages();
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	
}
