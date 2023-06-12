package com.situp.backend.backend.dto;

import reactor.util.annotation.Nullable;

import java.util.Date;

public class FiltreDto {
    @Nullable
    String city;
    @Nullable
    int postalcode;
    @Nullable
    String description;
    @Nullable
    int numberOfBeds;
    @Nullable
    int squareMeters;
    @Nullable
    Date startDate;
    @Nullable
    Date stopDate;
    @Nullable
    int numberPeopleMax;
    @Nullable
    int numberOfRooms;
}
