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
    @NotEmpty
    private boolean cat;
    @NotEmpty
    private boolean dog;
    @NotEmpty
    private boolean frog;
    @NotEmpty
    private boolean rabbit;
    @NotEmpty
    private boolean turtle;
    @NotEmpty
    private boolean fish;
    @NotEmpty
    private boolean snake;
    @NotEmpty
    private boolean bird;
    @NotEmpty
    private boolean hamster;



    @NotEmpty
    private boolean petsitting;
    @NotEmpty
    private boolean plantsitting;
    @NotEmpty
    private boolean housesitting;
    @NotEmpty
    private boolean house;
    @NotEmpty
    private boolean appartement;



    @NotEmpty
    private boolean children;
    @NotEmpty
    private boolean otherPet;
    @NotEmpty
    private boolean smoking;
    @NotEmpty
    private boolean wifi;


}
