package com.rentals.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentals.api.model.Messages;

public interface MessagesRepository  extends JpaRepository<Messages, Long> {

}
