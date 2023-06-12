package com.situp.backend.backend.dto;

import com.situp.backend.backend.database.Announcement;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
@Data
public class PreferenceDto {

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

    private Announcement announcement;
}
