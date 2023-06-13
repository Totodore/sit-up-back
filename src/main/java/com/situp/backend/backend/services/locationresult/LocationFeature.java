package com.situp.backend.backend.services.locationresult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationFeature {
    public String type;
    public LocationGeometry geometry;
    public LocationProperties properties;
}
