package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {

}
