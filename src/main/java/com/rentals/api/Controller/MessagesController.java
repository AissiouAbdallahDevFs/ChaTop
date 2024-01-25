package com.rentals.api.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentals.api.dto.PostMessagesDto;
import com.rentals.api.model.Messages;
import com.rentals.api.model.Rentals;
import com.rentals.api.model.User;
import com.rentals.api.repository.RentalsRepository;
import com.rentals.api.repository.UserRepository;
import com.rentals.api.service.MessagesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
@Api(tags = "Messages", description = "Operations related to messages")
public class MessagesController {

    @Autowired
    RentalsRepository rentalsRepository;

    @Autowired
    UserRepository userRepository;

    private final MessagesService  messagesService;
    
    @GetMapping("messages")
    @ApiOperation(value = "Get all messages", notes = "Returns a list of all messages.")
    public ResponseEntity<Iterable<Messages>> getMessages() {
        Iterable<Messages> messages = messagesService.getMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("messages")
    @ApiOperation(value = "Create a new message", notes = "Creates a new message.")
    public Messages createMessage(@RequestBody PostMessagesDto postMessagesDto) {
    	Messages messages = new Messages();
    	User user = userRepository.findById(postMessagesDto.getUser_id()).get();
        Rentals rentals = rentalsRepository.findById(postMessagesDto.getRental_id()).get();
    	messages.setMessage(postMessagesDto.getMessage());
    	messages.setRental(rentals);
    	messages.setUser(user);
    	
        return messagesService.saveMessages(messages);
    }
}

