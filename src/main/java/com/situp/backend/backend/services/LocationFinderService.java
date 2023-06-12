package com.situp.backend.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.situp.backend.backend.services.locationresult.LocationProperties;
import com.situp.backend.backend.services.locationresult.LocationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Service
public class LocationFinderService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public LocationProperties getLocation(String address, String city) throws IOException, InterruptedException {
        String API_URI = "https://api-adresse.data.gouv.fr/search?q=%s";

        URI uri = URI.create(String.format(API_URI, address + " " + city));

        var response = httpClient.send(HttpRequest.newBuilder().GET().uri(uri).build(), HttpResponse.BodyHandlers.ofString());
        LocationResult result = new ObjectMapper().readValue(response.body(), LocationResult.class);
        return result.getFeatures().get(0).locationProperties;
    }
}
