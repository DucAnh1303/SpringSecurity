package com.example.security.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "role")
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name")
    private String roleName;
}
