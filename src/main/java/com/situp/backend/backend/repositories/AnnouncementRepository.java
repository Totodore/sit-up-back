package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.Announcement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<AnnouncementRepository, Long> {
    @Query("SELECT * FROM Announcement ORDER BY id")
    Iterable<Announcement> findAllAnnoucement();


}


