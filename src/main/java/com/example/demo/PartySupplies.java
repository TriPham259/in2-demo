package com.example.demo;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PartySupplies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;
    private int quantity;
    private RentalStatus status;

    public PartySupplies(String itemName, int quantity, RentalStatus status) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.status = status;
    }
}
