package com.rentals.api.Controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentals.api.Dto.PostMessagesDto;
import com.rentals.api.Service.MessagesService;
import com.rentals.api.model.Messages;
import com.rentals.api.model.Rentals;
import com.rentals.api.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
@Api(tags = "Messages", description = "Operations related to messages")
public class MessagesController {

    
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
    	Rentals rentals = new Rentals();
    	Messages messages = new Messages();
    	User user = new User();
    	rentals.setId(postMessagesDto.getRentals_id());
    	messages.setMessage(postMessagesDto.getMessage());
    	user.setId(postMessagesDto.getUser_id());
    	messages.setRental(rentals);
    	messages.setUser(user);
    	
        return messagesService.saveMessages(messages);
    }
}

