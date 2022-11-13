package com.example.spring_test.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "donation")
@Getter
@Setter
public class Donation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", length = 300)
    private String text;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "nickname")
    private String userNickName;

    @Column(name = "date")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection_id;
}
