package com.situp.backend.backend.services.locationresult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class LocationGeometry {
    public String type;
    public ArrayList<Double> coordinates;
}
