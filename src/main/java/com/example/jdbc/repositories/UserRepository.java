package com.example.jdbc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jdbc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides CRUD methods automatically
}
