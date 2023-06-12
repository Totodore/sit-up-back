package com.situp.backend.backend.dto;

import lombok.Data;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class SearchAnnouncementDto {
    @NotEmpty
    int x;
    @NotEmpty
    int y;
    @NotEmpty
    int range;

    @Nullable
    Integer numberOfBeds;
    @Nullable
    Integer squareMeters;
    @Nullable
    Date startDate;
    @Nullable
    Date stopDate;
    @Nullable
    Integer numberPeople;
    @Nullable
    Integer numberOfRooms;
}
