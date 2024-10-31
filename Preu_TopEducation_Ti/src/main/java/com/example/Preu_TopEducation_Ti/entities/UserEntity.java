package com.example.Preu_TopEducation_Ti.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "UserEntity")
@NoArgsConstructor
@Data
@AllArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String username;
    private String password;
    private String email;
    private String role;
}
