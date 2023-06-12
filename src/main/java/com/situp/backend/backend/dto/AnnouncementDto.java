package com.situp.backend.backend.dto;

import com.situp.backend.backend.database.Announcement;
import com.situp.backend.backend.database.User;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class AnnouncementDto {


    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotEmpty
    private int postalcode;
    @NotEmpty
    private String description;
    @NotEmpty
    @Size(min = 1, max = 40)
    private int numberOfBeds;
    @NotEmpty
    @Size(min = 5, max = 400)
    private int squareMeters;
    @NotEmpty
    @DateTimeFormat
    private Date startDate;
    @NotEmpty
    @DateTimeFormat
    private Date stopDate;
    @NotEmpty
    @Size(min = 5, max = 40)
    private int numberPeopleMax;
    @NotEmpty
    @Size(min = 5, max = 20)
    private int numberOfRooms;

    @NotEmpty
    private User author;

    private boolean cat;

    private boolean dog;

    private boolean frog;

    private boolean rabbit;

    private boolean turtel;

    private boolean fish;

    private boolean snake;

    private boolean bird;

    private boolean hamster;

    private boolean paris;

    private boolean marseille;


    private boolean lyon;

    private boolean toulouse;

    private boolean nice;

    private boolean nantes;

    private boolean montpellier;

    private boolean strasbourg;

    private boolean petsitting;

    private boolean plantsitting;

    private boolean housesitting;

    private boolean house;

    private boolean appartment;

    private boolean children;

    private boolean otherPet;

    private boolean smoke;


}
