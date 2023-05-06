package com.example.backend.model;

import com.example.demo.RentalStatus;

import javax.persistence.*;

@Entity
@Table(name = "party_items")
public class PartySupplies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private RentalStatus status;

    public PartySupplies() {}

    public PartySupplies(String itemName, int quantity, RentalStatus status) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

}
