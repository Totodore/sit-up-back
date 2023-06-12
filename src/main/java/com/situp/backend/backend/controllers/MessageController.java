package com.situp.backend.backend.controllers;

import com.situp.backend.backend.config.jwt.TokenPayload;
import com.situp.backend.backend.database.Message;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.AddMessageDto;
import com.situp.backend.backend.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;

    @GetMapping("latest")
    public Iterable<Message> getLatestMessages(@AuthenticationPrincipal TokenPayload token) {
        return messageRepository.findAllByReceiverId(token.id());
    }

    @GetMapping("conversation/{id}")
    public Iterable<Message> getConversationMessage(@AuthenticationPrincipal TokenPayload token, @PathVariable Long id) {
        return messageRepository.findAllByReceiverIdAndSenderId(token.id(), id);
    }

    @PostMapping()
    public Message sendMessage(@AuthenticationPrincipal TokenPayload token, @RequestBody AddMessageDto body) {
        Message message = new Message();

        User author = new User();
        author.setId(token.id());
        message.setSender(author);

        User receiver = new User();
        receiver.setId(body.getReceiverId());
        message.setReceiver(receiver);

        message.setMessage(body.getMessage());
        return messageRepository.save(message);
    }
}
