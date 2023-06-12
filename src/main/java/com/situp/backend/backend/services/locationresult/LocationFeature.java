package com.situp.backend.backend.services.locationresult;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LocationFeature {
    public String type;
    public LocationGeometry locationGeometry;
    public LocationProperties locationProperties;
}
