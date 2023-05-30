package com.situp.backend.backend.controllers;

import com.situp.backend.backend.config.jwt.TokenPayload;
import com.situp.backend.backend.database.Message;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.AddMessageDto;
import com.situp.backend.backend.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;

    @GetMapping("subscribe")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
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
