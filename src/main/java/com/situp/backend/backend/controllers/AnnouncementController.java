package com.situp.backend.backend.controllers;

import com.situp.backend.backend.database.Announcement;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.AuthLoginView;
import com.situp.backend.backend.dto.AuthRegisterDto;
import com.situp.backend.backend.dto.UserView;
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
import java.util.List;

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

    @PostMapping("/add/{id}")
    public Announcement addAnnouncement(@PathVariable Long id) {

        Date debut = new Date();
        Date fin = new Date();
        LOG.info("creating announcement ");
        User user = new User();
        Announcement announcement = new Announcement();
        announcement.setAuthor(user);
        announcement.setAddress("88 rue de varenne");
        announcement.setCity("Paris");
        announcement.setPostalcode(75007);
        announcement.setId(id);
        announcement.setDescription("rbvfvbgfvb");
        announcement.setNumberOfBeds(3);
        announcement.setNumberOfRooms(2);
        announcement.setNumberPeopleMax(4);
        announcement.setSquareMeters(50);
        announcement.setStartDate(debut);
        announcement.setStopDate(fin);
        return announcement;
    }

    @GetMapping("/filter")
    public Iterable<Announcement> filterAnnouncements(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "postalcode", required = false) int postalcode,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "numberOfBeds", required = false) int numberOfBeds,
            @RequestParam(value = "squareMeters", required = false) int squareMeters,
            @RequestParam(value = "startDate", required = false) Date startDate,
            @RequestParam(value = "stopDate", required = false) Date stopDate,
            @RequestParam(value = "numberPeopleMax", required = false) int numberPeopleMax,
            @RequestParam(value = "numberOfRooms", required = false) int numberOfRooms
    ) {
        return announcementRepository.findFilteredAnnouncements(
                city, postalcode, description, numberOfBeds, squareMeters,
                startDate, stopDate, numberPeopleMax, numberOfRooms
        );
    }
}
