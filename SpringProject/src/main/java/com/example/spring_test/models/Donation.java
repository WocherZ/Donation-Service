package com.example.spring_test.models;

import javax.persistence.*;

@Entity
@Table(name = "donation")
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
    private String userNickname;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection_id;
}
