package com.tastyfoodwebapplication.repositories;

import com.tastyfoodwebapplication.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}
