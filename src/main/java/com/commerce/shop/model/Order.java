package com.commerce.shop.model;

import com.commerce.shop.model.dto.RandomCode;
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
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    private String code = RandomCode.getRandomString(12);

    @OneToMany(mappedBy = "order" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private int status;
}
