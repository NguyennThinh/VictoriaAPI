package com.commerce.shop.model;

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
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String saleName;

    private int saleValue;

    private String startDate;

    private String endDate;

    private boolean status;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY )
    private List<Product> productsSale;
}
