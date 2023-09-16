package com.commerce.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @Column(columnDefinition = "nvarchar(255)")
    private String saleName;

    private int saleValue;

    private Date startDate;

    private Date endDate;


    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY )
    @JsonIgnore
    private List<Product> productsSale;
}
