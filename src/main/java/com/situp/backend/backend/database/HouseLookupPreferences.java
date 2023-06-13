package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Getter
@Setter
public class HouseLookupPreferences {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    //Activity
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<HouseActivity> activities;

    //Housing
    private HousingType housingType;

    //Animals
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Animal> refusedAnimals;

    //Other
    private Boolean allowedChildren = true;
    private Boolean allowedPets = false;
    private Boolean allowedSmoking = false;
    private Boolean wifi = true;
}

