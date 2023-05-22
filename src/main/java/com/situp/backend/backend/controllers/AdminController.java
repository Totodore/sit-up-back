package com.situp.backend.backend.controllers;


import com.situp.backend.backend.repositories.AnnouncementRepository;
import com.situp.backend.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@RolesAllowed("ROLE_ADMIN")
public class AdminController {

    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;

    @PatchMapping("/user/{id}/toggleAdmin")
    public void toggleAdmin(@PathVariable Long id) {
        userRepository.toggleAdmin(id);
        LOG.info("user {} is now admin", id);
    }

    @DeleteMapping("/announcement/{id}")
    public void deleteAnnouncement(@PathVariable Long id) {
        announcementRepository.deleteById(id);
        LOG.info("announcement {} deleted", id);
    }
}
