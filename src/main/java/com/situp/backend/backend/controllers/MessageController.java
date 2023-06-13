package com.situp.backend.backend.controllers;

import com.situp.backend.backend.config.jwt.TokenPayload;
import com.situp.backend.backend.database.Message;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.AddMessageDto;
import com.situp.backend.backend.repositories.MessageRepository;
import com.situp.backend.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    private final HashMap<Long, FluxSink<ServerSentEvent<Message>>> streams = new HashMap<>();

    @GetMapping("subscribe")
    public Flux<ServerSentEvent<Message>> streamEvents(@AuthenticationPrincipal TokenPayload token) {
        LOG.info("user {} subscribed to messages", token.id());
        return Flux.create(emitter -> {
            emitter.onDispose(() -> {
                LOG.info("user {} unsubscribed from messages", token.id());
                streams.remove(token.id());
            });
            streams.put(token.id(), emitter);
        });
    }

    @GetMapping("latest")
    public Iterable<Message> getLatestMessages(@AuthenticationPrincipal TokenPayload token) {
        return messageRepository.getLatestReceived(token.id());
    }

    @GetMapping("conversation/{id}")
    public Iterable<Message> getConversationMessage(@AuthenticationPrincipal TokenPayload token, @PathVariable Long id) {
        return messageRepository.findAllByReceiverIdAndSenderId(token.id(), id);
    }

    @PostMapping()
    public Message sendMessage(@AuthenticationPrincipal TokenPayload token, @RequestBody AddMessageDto body) {
        Message message = new Message();

        User author = userRepository.findById(token.id()).get();
        message.setSender(author);

        User receiver = userRepository.findById(body.getReceiverId()).get();
        message.setReceiver(receiver);
        message.setMessage(body.getMessage());
        message.setDate(new Date());
        message = messageRepository.save(message);

        LOG.info("user {} sent message to user {}", token.id(), body.getReceiverId());
        if (streams.containsKey(body.getReceiverId())) {
            streams.get(body.getReceiverId()).next(ServerSentEvent.builder(message).build());
        }
        streams.get(token.id()).next(ServerSentEvent.builder(message).build());
        return message;
    }
}
