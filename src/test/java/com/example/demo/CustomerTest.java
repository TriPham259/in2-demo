package com.example.demo;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
public class CustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @BeforeEach
    void setUp() {
        this.reservationRepository.deleteAll();
        this.customerRepository.deleteAll();
    }

    @Test
    void getAllCustomersSuccess() {
        Customer customer1 = new Customer("Tri", "Pham");
        customerRepository.save(customer1);

        Customer customer2 = new Customer("Saad", "Lionel");
        customerRepository.save(customer2);

        Assertions.assertThat(customerRepository.count()).isEqualTo(2);



    }



}
