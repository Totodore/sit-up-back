package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Announcement {

    @GeneratedValue
    @Id
    private long id;

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

    @ManyToOne
    private User author;
    @OneToMany
    private List<Image> image;

}
