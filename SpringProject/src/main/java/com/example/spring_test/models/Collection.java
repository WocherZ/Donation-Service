package com.example.spring_test.models;

import javax.persistence.*;

@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "current_amount")
    private Double currentAmount;

    @Column(name = "limit_amount")
    private Double limitAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

}
