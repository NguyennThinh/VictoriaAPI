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
public class Cart {

    @Id
    private String cid;


    @OneToMany(mappedBy = "cart", cascade =CascadeType.ALL,fetch = FetchType.EAGER,  orphanRemoval = true)
    private List<ItemCart> items;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private User user;


    public void addToChildren(ItemCart child) {
        child.setCart(this);
        this.items.add(child);
    }



}
