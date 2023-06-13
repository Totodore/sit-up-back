package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value = "SELECT * FROM message WHERE id IN (SELECT max(id) FROM message GROUP BY sender_id)", nativeQuery = true)
    Iterable<Message> getLatestReceived(Long id);

    @Query("SELECT m FROM Message m WHERE (m.receiver.id = ?1 AND m.sender.id = ?2) OR (m.sender.id = ?1 AND m.receiver.id = ?2) ORDER BY m.date DESC")
    Iterable<Message> findAllByReceiverIdAndSenderId(Long receiverId, Long senderId);
}
