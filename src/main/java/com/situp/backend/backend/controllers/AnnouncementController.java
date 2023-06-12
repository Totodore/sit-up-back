package com.situp.backend.backend.controllers;

import com.situp.backend.backend.database.*;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        announcement.setAllowedChildren(body.isChildren());
        announcement.setAllowedPets(body.isOtherPet());
        announcement.setAllowedSmoking(body.isSmoking());
        announcement.setWifi(body.isWifi());
        Set<HouseActivity> setActivity = new HashSet(Arrays.asList( body.isPetsitting(), body.isPlantsitting(),body.isHousesitting()));
        announcement.setActivities(setActivity);
        //HousingType setHousing = body.isHouse();
        //announcement.setHousingType(setHousing);
        Set<Animal> setAnimal = new HashSet(Arrays.asList( body.isCat(), body.isDog(),body.isFrog(),body.isRabbit(),body.isTurtle(),body.isFish(),body.isSnake(),body.isBird(),body.isHamster()));
        announcement.setRefusedAnimals(setAnimal);
        announcementRepository.save(announcement);





    }



}
