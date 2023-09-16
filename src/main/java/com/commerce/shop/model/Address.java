package com.commerce.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    @Column(columnDefinition = "nvarchar(255)")
    private String country;

    @Column(columnDefinition = "nvarchar(255)")
    private String state;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
