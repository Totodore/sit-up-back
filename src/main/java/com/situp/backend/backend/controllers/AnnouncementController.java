package com.situp.backend.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.situp.backend.backend.config.jwt.TokenPayload;
import com.situp.backend.backend.database.Announcement;
import com.situp.backend.backend.database.HouseLookupPreferences;
import com.situp.backend.backend.database.User;
import com.situp.backend.backend.dto.AddAnnouncementDto;
import com.situp.backend.backend.dto.SearchAnnouncementDto;
import com.situp.backend.backend.repositories.AnnouncementRepository;
import com.situp.backend.backend.repositories.UserRepository;
import com.situp.backend.backend.services.LocationFinderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final Logger LOG = LoggerFactory.getLogger(AnnouncementController.class);

    private final AnnouncementRepository announcementRepository;

    private final LocationFinderService locationFinder;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @GetMapping("all")
    public Iterable<Announcement> getAllAnnouncement() {
        return announcementRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Announcement getAnnouncement(@PathVariable Long id) {

        LOG.info("announcement {} found ", announcementRepository.findById(id).get());
        LOG.info("test");
        return announcementRepository.findById(id).get();
    }

    @PostMapping("/add")
    public Announcement addAnnouncement(@AuthenticationPrincipal TokenPayload token, @RequestBody AddAnnouncementDto body) throws IOException, InterruptedException {

        var location = locationFinder.getLocation(body.getAddress());

        User user = userRepository.findById(token.id()).get();
        Announcement announcement = mapper.map(body, Announcement.class);
        announcement.setAuthor(user);
        announcement.setPostalcode(Integer.parseInt(location.getPostcode()));
        announcement.setCity(location.getCity());
        announcement.setX((int) location.getX());
        announcement.setY((int) location.getY());

        announcementRepository.save(announcement);

        return announcement;
    }

    @PostMapping("/search")
    public Iterable<Announcement> searchAnnouncements(@AuthenticationPrincipal TokenPayload token, @RequestBody SearchAnnouncementDto body) {
        LOG.info("searching announcements");
        var prefs = userRepository.findHouseLookupPreferencesByUserId(token.id());

        return announcementRepository.findAll(getAnnouncementQuery(body, prefs));
    }

    private Specification<Announcement> getAnnouncementQuery(SearchAnnouncementDto dto, Optional<HouseLookupPreferences> prefs) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Coordonnées
            predicates.add(criteriaBuilder.greaterThan(root.get("x"), dto.getX() - dto.getRange() * 1000));
            predicates.add(criteriaBuilder.lessThan(root.get("x"), dto.getX() + dto.getRange() * 1000));
            predicates.add(criteriaBuilder.greaterThan(root.get("y"), dto.getY() - dto.getRange() * 1000));
            predicates.add(criteriaBuilder.lessThan(root.get("y"), dto.getY() + dto.getRange() * 1000));

            // Critères de recherches depuis les préférences de l'utilisateur
            if (prefs.isPresent()) {
                var pref = prefs.get();
                if (pref.getWifi() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("wifi"), pref.getWifi()));
                }
                if (pref.getAllowedChildren() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("allowedChildren"), pref.getAllowedChildren()));
                }
                //if (pref.getActivities() != null) {
                  //  predicates.add(root.get("activities").in(pref.getActivities()));
                //}
                if (pref.getAllowedPets() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("allowedPets"), pref.getAllowedPets()));
                }
                if (pref.getAllowedSmoking() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("allowedSmoking"), pref.getAllowedSmoking()));
                }
                if (pref.getHousingType() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("housingType"), pref.getHousingType()));
                }
                //if (pref.getRefusedAnimals() != null) {
                  //  predicates.add(root.get("refusedAnimals").in(pref.getRefusedAnimals()));
                //}
            }

            // Critères de recherches depuis le formulaire (SearchAnnouncementDto)
            if (dto.getNumberOfBeds() != null) {
                predicates.add(criteriaBuilder.equal(root.get("numberOfBeds"), dto.getNumberOfBeds()));
            }
            if (dto.getNumberPeople() != null) {
                predicates.add(criteriaBuilder.equal(root.get("numberPeopleMax"), dto.getNumberPeople()));
            }
            if (dto.getSquareMeters() != null) {
                predicates.add(criteriaBuilder.equal(root.get("squareMeters"), dto.getSquareMeters()));
            }
            if (dto.getStartDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("startDate"), dto.getStartDate()));
            }
            if (dto.getStopDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("stopDate"), dto.getStopDate()));
            }
            if (dto.getNumberOfRooms() != null) {
                predicates.add(criteriaBuilder.equal(root.get("numberOfRooms"), dto.getNumberOfRooms()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
