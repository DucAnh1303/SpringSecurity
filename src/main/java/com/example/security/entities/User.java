package com.example.security.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;
}
