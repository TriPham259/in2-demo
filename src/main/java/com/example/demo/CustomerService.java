package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    public CustomerService (CustomerRepository customerRepository, ReservationRepository reservationRepository) {
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservationOf(Customer customer) {
        return reservationRepository.findByCustomer(customer);
    }



}
