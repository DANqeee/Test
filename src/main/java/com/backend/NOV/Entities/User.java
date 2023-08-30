package com.backend.NOV.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String email;
    private String password;
    private Date birthday;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isAuthorized;

    private String code;
}
