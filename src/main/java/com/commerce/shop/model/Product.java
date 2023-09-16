package com.commerce.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String code;

    @Column(columnDefinition = "nvarchar(255)")
    private String productName;

    private double price;

    private int quantity;

    @Column(columnDefinition = "nvarchar(MAX)")

    private String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<ProductImage> productImage;

    private int status;


    private int quantitySold;


    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews;


}
