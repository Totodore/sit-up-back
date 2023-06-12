package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.Announcement;
import com.situp.backend.backend.dto.FiltreDto;
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
            " AND (:postalcode IS NULL OR postalcode = :postalcode)" +
            " AND (:numberOfBeds IS NULL  OR numberOfBeds = :numberOfBeds)" +
            " AND (:squareMeters IS NULL  OR squareMeters = :squareMeters)" +
            " AND (:startDate IS NULL OR startDate = :startDate)" +
            " AND (:stopDate IS NULL OR stopDate = :stopDate)" +
            " AND (:numberPeopleMax IS NULL  OR numberPeopleMax = :numberPeopleMax)" +
            " AND (:numberOfRooms = 0 OR numberOfRooms = :numberOfRooms)",
            nativeQuery = true)
    List<Announcement> findFilteredAnnouncements( FiltreDto filtre);
}
