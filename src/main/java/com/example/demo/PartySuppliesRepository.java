package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartySuppliesRepository extends JpaRepository<PartySupplies,Long> {
    public List<PartySupplies> findByItemName (String itemName);

}
