package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.HouseLookupPreferences;
import com.situp.backend.backend.database.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HouseLookupPreferencesRepository extends CrudRepository<HouseLookupPreferences, Long> {
    @Query(value = "SELECT h FROM HouseLookupPreferences h WHERE h.user = ?1")
    public Optional<HouseLookupPreferences> findByUserId(User user);
}
