package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.Announcement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {

    @Query(value = "SELECT a FROM Announcement a WHERE a.x >= :x - :range AND a.x <= :x + :range AND a.y >= :y - :range AND a.y <= :y + :range")
    List<Announcement> findAnnouncementsInRange(int x, int y, int range);

}
