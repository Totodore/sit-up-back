package com.situp.backend.backend.repositories;

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

    @Query("UPDATE User u SET u.admin = NOT u.admin WHERE u.id = ?1")
    @Modifying
    void toggleAdmin(Long id);
}
