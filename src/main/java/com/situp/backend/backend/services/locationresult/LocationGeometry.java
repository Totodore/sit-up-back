package com.situp.backend.backend.services.locationresult;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Getter
@Setter
class LocationGeometry {
    public String type;
    public ArrayList<Double> coordinates;
}
