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
public class LocationResult {
    public String type;
    public String version;
    public ArrayList<LocationFeature> features;
    public String attribution;
    public String licence;
    public String query;
    public int limit;
}
