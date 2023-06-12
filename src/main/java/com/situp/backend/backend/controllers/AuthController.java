package com.situp.backend.backend.controllers;

import com.situp.backend.backend.config.jwt.JwtTokenUtil;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.AuthLoginDto;
import com.situp.backend.backend.dto.AuthLoginView;
import com.situp.backend.backend.dto.AuthRegisterDto;
import com.situp.backend.backend.dto.UserView;
import com.situp.backend.backend.exceptions.HttpBadRequestException;
import com.situp.backend.backend.exceptions.HttpForbiddenException;
import com.situp.backend.backend.exceptions.HttpNotFoundException;
import com.situp.backend.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public AuthLoginView login(@RequestBody AuthLoginDto body) {
        User user = userRepository.findByEmail(body.getEmail()).orElseThrow(() -> new HttpNotFoundException("email not found"));

        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new HttpForbiddenException("wrong password");
        }

        LOG.info("user logged in: {}", body.getEmail());
        String token = jwtTokenUtil.generateToken(user);
        UserView userView = modelMapper.map(user, UserView.class);
        return new AuthLoginView(token, userView);
    }

    @PostMapping("/register")
    public AuthLoginView register(@RequestBody AuthRegisterDto body) {
        if (userRepository.existsByEmail(body.getEmail())) {
            throw new HttpBadRequestException("email already exists");
        }

        LOG.info("creating user with email {}", body.getEmail());
        User user = new User();
        user.setEmail(body.getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user.setName(body.getName());
        user.setFirstname(body.getFirstname());
        user.setBirthdate(body.getBirthdate());
        user.setAdmin(false);
        user.setPrefs(body.getHouseLookupPreferenceList());

        userRepository.save(user);
        String token = jwtTokenUtil.generateToken(user);
        UserView userView = modelMapper.map(user, UserView.class);
        return new AuthLoginView(token, userView);
    }
}
