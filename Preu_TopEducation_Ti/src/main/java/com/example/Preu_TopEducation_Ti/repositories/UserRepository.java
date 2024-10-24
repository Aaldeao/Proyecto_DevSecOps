package com.example.Preu_TopEducation_Ti.repositories;

import com.example.Preu_TopEducation_Ti.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    ArrayList<UserEntity> findByUsername(String username);
    ArrayList<UserEntity> findByEmail(String email);
    ArrayList<UserEntity> findByRole(String role);
}
