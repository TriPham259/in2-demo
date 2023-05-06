package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@Component
class PopulateTestDataRunner implements CommandLineRunner {


	private final CustomerRepository customerRepository;
	private final PartySuppliesRepository partySuppliesRepository;
	private final ReservationRepository reservationRepository;

	@Autowired
	private PartyService partyService;

	public PopulateTestDataRunner (CustomerRepository customerRepository, PartySuppliesRepository partySuppliesRepository,
								   ReservationRepository reservationRepository) {
		this.customerRepository = customerRepository;
		this.partySuppliesRepository = partySuppliesRepository;
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void run(String... args) {

		reservationRepository.deleteAll();
		customerRepository.deleteAll();
		partySuppliesRepository.deleteAll();

		Customer customer1 = new Customer("Tri", "Pham");
		customerRepository.save(customer1);

		Customer customer2 = new Customer("Saad", "Lionel");
		customerRepository.save(customer2);

		PartySupplies partySupplies1 = new PartySupplies("Tisch",10, RentalStatus.AVAILABLE);
		partySuppliesRepository.save(partySupplies1);

		PartySupplies partySupplies2 = new PartySupplies("Sofa",10, RentalStatus.AVAILABLE);
		partySuppliesRepository.save(partySupplies2);

		Reservation reservation1 = new Reservation(partySupplies1, customer1);
		reservationRepository.save(reservation1);

		Reservation reservation2 = new Reservation(partySupplies2, customer2);
		reservationRepository.save(reservation2);

		partyService.rentItems(partySupplies1, customer1);
		partyService.rentItems(partySupplies2, customer2);

	}
}