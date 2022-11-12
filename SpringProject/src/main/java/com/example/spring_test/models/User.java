package com.example.spring_test.models;

import javax.persistence.*;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "login")
    private String login;

    @Column(name = "password", length = 2000)
    private String password;
}
