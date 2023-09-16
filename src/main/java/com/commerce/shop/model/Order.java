package com.commerce.shop.model;

import com.commerce.shop.model.dto.RandomCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    private String code = RandomCode.getRandomString(12);

    @OneToMany(mappedBy = "order" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @OneToOne
    private Address address;

    private int status;

    private String paymentMethod;

    private String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
}
