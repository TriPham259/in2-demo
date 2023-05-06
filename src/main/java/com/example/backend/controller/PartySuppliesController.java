package com.example.backend.controller;

import com.example.backend.model.PartySupplies;
import com.example.backend.repository.PartySuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PartySuppliesController {

    @Autowired
    private PartySuppliesRepository partySuppliesRepository;

    @GetMapping("party_items")
    public List<PartySupplies> getPartySupplies(){
        return this.partySuppliesRepository.findAll();
    }
}
