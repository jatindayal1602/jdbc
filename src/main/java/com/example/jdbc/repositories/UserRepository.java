package com.example.jdbc.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jdbc.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides CRUD methods automatically
        @Query("SELECT u FROM User u WHERE u.name = :name")
    Optional<User> findByName(@Param("name") String name);
}
