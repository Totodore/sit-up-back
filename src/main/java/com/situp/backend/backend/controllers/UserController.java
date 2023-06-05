package com.situp.backend.backend.controllers;

import com.situp.backend.backend.config.jwt.TokenPayload;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.UserView;
import com.situp.backend.backend.exceptions.HttpNotFoundException;
import com.situp.backend.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @GetMapping("/me")
    public UserView getMe(@AuthenticationPrincipal TokenPayload token) {
        User user = userRepository.findById(token.id()).orElseThrow(() -> new HttpNotFoundException("user not found"));
        return modelMapper.map(user, UserView.class);
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    public Iterable<UserView> getAll(@RequestParam("search") Optional<String> search) {
        if (search.isPresent()) {
            return Stream.of(userRepository.findAllByEmailContaining(search.get())).map(user -> modelMapper.map(user, UserView.class)).toList();
        } else {
            return Stream.of(userRepository.findAll()).map(user -> modelMapper.map(user, UserView.class)).toList();
        }
    }

}
