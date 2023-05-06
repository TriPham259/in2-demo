package com.example.demo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

@Service
public class PartyService {

    private final ReservationRepository reservationRepository;
    private final PartySuppliesRepository partySuppliesRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PartyService(ReservationRepository reservationRepository, PartySuppliesRepository partySuppliesRepository,
                        CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.partySuppliesRepository = partySuppliesRepository;
        this.customerRepository = customerRepository;
    }


    public Reservation rentItems(PartySupplies items, Customer customer) {
        if (items.getStatus() == RentalStatus.RENTED) {
            throw new IllegalStateException("This item is already rented.");
        }
        if (items.getQuantity() == 0) {
            throw new IllegalStateException("This item is out of stock.");
        }

        items.setStatus(RentalStatus.RENTED);
        items.setQuantity(items.getQuantity() - 1);
        partySuppliesRepository.save(items);

        Reservation reservation = new Reservation(items, customer);
        reservationRepository.save(reservation);

        return reservation;
    }

    public Reservation returnItems(Reservation reservation) {
        PartySupplies items = reservation.getPartySupplies();
        items.setStatus(RentalStatus.AVAILABLE);
        items.setQuantity(items.getQuantity() + 1);
        partySuppliesRepository.save(items);

        reservationRepository.delete(reservation);

        return reservation;
    }

    public void reportDefectiveItems(PartySupplies items) {
        items.setStatus(RentalStatus.DEFECTIVE);
        partySuppliesRepository.save(items);
    }

    public void addSupplies(PartySupplies partySupplies) {
        partySuppliesRepository.save(partySupplies);
    }

    public void removeSupplies(PartySupplies partySupplies) {
        partySuppliesRepository.delete(partySupplies);
    }

    public void markAsRepaired(PartySupplies items) {
        items.setStatus(RentalStatus.REPAIRED);
        partySuppliesRepository.save(items);
    }
}
