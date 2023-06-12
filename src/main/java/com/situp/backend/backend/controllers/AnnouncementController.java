package com.situp.backend.backend.controllers;

import com.situp.backend.backend.database.Announcement;
import com.situp.backend.backend.database.Preferences;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.*;
import com.situp.backend.backend.exceptions.HttpBadRequestException;
import com.situp.backend.backend.repositories.AnnouncementRepository;
import com.situp.backend.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final Logger LOG = LoggerFactory.getLogger(AnnouncementController.class);

    private final AnnouncementRepository announcementRepository;

    @GetMapping("all")
    public Iterable<Announcement> getAllAnnouncement() {
        return announcementRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Announcement getAnnouncement(@PathVariable Long id) {

        LOG.info("announcement {} found ", announcementRepository.findById(id).get());
        LOG.info("test");
        return announcementRepository.findById(id).get();
    }

    @PostMapping("/add")
    public void addAnnouncement(@RequestBody AnnouncementDto body ) {


        Announcement announcement = new Announcement();
        announcement.setAuthor(body.getAuthor());
        announcement.setAddress(body.getAddress());
        announcement.setCity(body.getCity());
        announcement.setPostalcode(body.getPostalcode());

        announcement.setDescription(body.getDescription());
        announcement.setNumberOfBeds(body.getNumberOfBeds());
        announcement.setNumberOfRooms(body.getNumberOfRooms());
        announcement.setNumberPeopleMax(body.getNumberPeopleMax());
        announcement.setSquareMeters(body.getSquareMeters());
        announcement.setStartDate(body.getStartDate());
        announcement.setStopDate(body.getStopDate());
        announcementRepository.save(announcement);





    }



}
