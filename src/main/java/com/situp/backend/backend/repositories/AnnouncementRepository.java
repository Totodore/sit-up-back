package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.Announcement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
    @Query(value = "SELECT * FROM Announcement WHERE 1=1" +
            " AND (:city IS NULL OR city = :city)" +
            " AND (:postalcode = 0 OR postalcode = :postalcode)" +
            " AND (:description IS NULL OR description = :description)" +
            " AND (:numberOfBeds = 0 OR numberOfBeds = :numberOfBeds)" +
            " AND (:squareMeters = 0 OR squareMeters = :squareMeters)" +
            " AND (:startDate IS NULL OR startDate = :startDate)" +
            " AND (:stopDate IS NULL OR stopDate = :stopDate)" +
            " AND (:numberPeopleMax = 0 OR numberPeopleMax = :numberPeopleMax)" +
            " AND (:numberOfRooms = 0 OR numberOfRooms = :numberOfRooms)",
            nativeQuery = true)
    List<Announcement> findFilteredAnnouncements(
            @Param("city") String city,
            @Param("postalcode") int postalcode,
            @Param("description") String description,
            @Param("numberOfBeds") int numberOfBeds,
            @Param("squareMeters") int squareMeters,
            @Param("startDate") Date startDate,
            @Param("stopDate") Date stopDate,
            @Param("numberPeopleMax") int numberPeopleMax,
            @Param("numberOfRooms") int numberOfRooms);

}
