package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Announcement {

    @GeneratedValue
    @Id
    private long id;

    private String address;

    private String city;

    private int x;
    private int y;

    private int postalcode;

    private String description;

    private int numberOfBeds;

    private int squareMeters;

    private Date startDate;

    private Date stopDate;

    private int numberPeopleMax;

    private int numberOfRooms;

    //Activity
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<HouseActivity> activities;

    //Housing
    private HousingType housingType;

    //Animals
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Animal> refusedAnimals;

    //Other
    private boolean allowedChildren;
    private boolean allowedPets;
    private boolean allowedSmoking;
    private boolean wifi;

    @ManyToOne
    private User author;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Image> images;

}
