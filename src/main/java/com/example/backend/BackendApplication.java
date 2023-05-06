package com.example.backend;

import com.example.backend.model.PartySupplies;
import com.example.backend.repository.PartySuppliesRepository;
import com.example.demo.RentalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Autowired
    private PartySuppliesRepository partySuppliesRepository;

    @Override
    public void run(String... args) throws Exception {
        this.partySuppliesRepository.save(new PartySupplies("Table", 100, RentalStatus.AVAILABLE));
        this.partySuppliesRepository.save(new PartySupplies("Chair", 50, RentalStatus.AVAILABLE));
        this.partySuppliesRepository.save(new PartySupplies("Speaker", 100, RentalStatus.AVAILABLE));
    }
}
