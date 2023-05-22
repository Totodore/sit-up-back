package com.situp.backend.backend.controllers;

import com.situp.backend.backend.database.Announcement;
import com.situp.backend.backend.repositories.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
private final AnnouncementRepository announcementRepository;
    @GetMapping("/home")
    public Iterable<Announcement> getListAnnouncement(){
        return announcementRepository.findAllAnnoucement();
    }
}
