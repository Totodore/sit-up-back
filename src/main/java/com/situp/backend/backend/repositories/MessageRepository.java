package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.receiver.id = ?1 ORDER BY m.date DESC")
    Iterable<Message> findAllByReceiverId(Long id);

    @Query("SELECT m FROM Message m WHERE m.receiver.id = ?1 AND m.sender.id = ?2 ORDER BY m.date DESC")
    Iterable<Message> findAllByReceiverIdAndSenderId(Long receiverId, Long senderId);
}
