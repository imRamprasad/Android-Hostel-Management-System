package com.Ram.backend.user.repository;

import com.Ram.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Standard CRUD methods are built-in
}