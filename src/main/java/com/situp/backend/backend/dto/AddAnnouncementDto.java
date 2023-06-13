package com.situp.backend.backend.dto;

import com.situp.backend.backend.database.Animal;
import com.situp.backend.backend.database.HouseActivity;
import com.situp.backend.backend.database.HousingType;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class AddAnnouncementDto {

    private String address;

    private String description;

    private Integer numberOfBeds;

    private Integer squareMeters;

    private Date startDate;

    private Date stopDate;

    private Integer numberPeopleMax;

    private Integer numberOfRooms;

    //Activity
    private Set<HouseActivity> activities;

    //Housing
    private HousingType housingType;

    //Animals
    private Set<Animal> refusedAnimals;

    //Other

    private Boolean allowedChildren;
    private Boolean allowedPets;
    private Boolean allowedSmoking;
    private Boolean wifi;
}
