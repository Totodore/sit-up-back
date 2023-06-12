package com.situp.backend.backend.repositories;

import com.situp.backend.backend.database.HouseLookupPreferences;
import com.situp.backend.backend.database.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT COUNT(u) = 1 FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.admin = CASE u.admin " +
            "WHEN TRUE THEN FALSE " +
            "WHEN FALSE THEN TRUE " +
            "ELSE u.admin " +
            "END WHERE u.id = ?1")
    void toggleAdmin(Long id);

    @Query("SELECT u FROM User u WHERE u.email LIKE %?1%")
    Iterable<User> findAllByEmailContaining(String search);

    @Query("SELECT l FROM HouseLookupPreferences l WHERE l.user.id = ?1")
    Optional<HouseLookupPreferences> findHouseLookupPreferencesByUserId(Long id);
}
