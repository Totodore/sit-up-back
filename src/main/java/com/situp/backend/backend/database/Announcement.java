package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Announcement {

    @GeneratedValue
    @Id
    private long id;

    private String authorId;

    private String address;

    private String city;

    private int postalcode;

    private String description;

    private int numberOfBeds;

    private int squareMeters;

    private Date startDate;

    private Date stopDate;

    private int numberPeopleMax;

    private int numberOfRooms;

}
