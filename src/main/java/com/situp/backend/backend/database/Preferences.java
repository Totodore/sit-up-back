package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Preferences {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    //Activity
    private boolean petsitting;
    private boolean plantsitting;
    private boolean housesitting;

    //Housing
    private boolean house;
    private boolean appartment;

    //Animals
    private boolean cat;
    private boolean dog;
    private boolean frog;
    private boolean rabbit;
    private boolean turtel;
    private boolean fish;
    private boolean snake;
    private boolean bird;
    private boolean hamster;

    //City
    private boolean paris;
    private boolean marseille;
    private boolean lyon;
    private boolean toulouse;
    private boolean nice;
    private boolean nantes;
    private boolean montpellier;
    private boolean strasbourg;

    //Other
    private boolean children;
    private boolean otherPet;
    private boolean smoke;
}

