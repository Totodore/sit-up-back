package com.situp.backend.backend.services.locationresult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationProperties {
    public String label;
    public double score;
    public String housenumber;
    public String id;
    public String type;
    public String name;
    public String postcode;
    public String citycode;
    public double x;
    public double y;
    public String city;
    public String context;
    public double importance;
    public String street;
}
