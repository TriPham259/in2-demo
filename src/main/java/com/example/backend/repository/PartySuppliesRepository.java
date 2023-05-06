package com.example.backend.repository;

import com.example.backend.model.PartySupplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartySuppliesRepository extends JpaRepository<PartySupplies, Long> {

}
