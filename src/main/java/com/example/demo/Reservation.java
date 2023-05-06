package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private PartySupplies partySupplies;

    @ManyToOne
    private Customer customer;


    public Reservation(PartySupplies partySupplies, Customer customer) {
        this.partySupplies = partySupplies;
        this.customer = customer;
    }
}
