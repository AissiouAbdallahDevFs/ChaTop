package com.rentals.api.service;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentals.api.model.Messages;
import com.rentals.api.repository.*;



@Service
public class MessagesService {
	@Autowired
	private MessagesRepository messagesRepository;
	
	public Iterable<Messages> getMessages() {
		return messagesRepository.findAll();
	}
	   
	public Messages saveMessages(Messages messages) {
		messages.setCreatedAt(java.time.LocalDateTime.now());
		Messages savedMessages = messagesRepository.save(messages);
		return savedMessages;
	}
}

