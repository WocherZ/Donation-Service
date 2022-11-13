package com.example.spring_test.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password", length = 2000)
    private String password;

    @Column(name = "urlWidget")
    private String urlWidget;

    @Column(name = "urlDonateForm")
    private String urlDonateForm;

    @Column(name = "about_user")
    private String aboutUser;

    @Column(name = "stream_content")
    private String streamContent;

    @Column(name = "channel_description")
    private String channelDescription;
}
