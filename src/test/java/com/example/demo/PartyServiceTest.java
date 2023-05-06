package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
public class PartyServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PartySuppliesRepository partySuppliesRepository;

    @Autowired
    private PartyService partyService;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        this.reservationRepository.deleteAll();
        this.customerRepository.deleteAll();
        this.partySuppliesRepository.deleteAll();
    }

    @Test
    public void testRentItems() {
        PartySupplies items = new PartySupplies("Tisch", 100, RentalStatus.AVAILABLE);
        partySuppliesRepository.save(items);

        Customer customer = new Customer("Saad", "Lionel");
        customerRepository.save(customer);

        Reservation reservation = partyService.rentItems(items, customer);

        assertEquals(items, reservation.getPartySupplies());
        assertEquals(customer, reservation.getCustomer());
        assertEquals(RentalStatus.RENTED, items.getStatus());
    }

    @Test
    public void testReturnItems() {
        Customer customer = new Customer("Saad", "Lionel");
        customerRepository.save(customer);

        PartySupplies items = new PartySupplies("Stuhl", 50, RentalStatus.AVAILABLE);
        partySuppliesRepository.save(items);

        Reservation reservation = partyService.rentItems(items,customer);

        partyService.returnItems(reservation);

        PartySupplies updatedItems = partySuppliesRepository.findById(items.getId()).get();
        assertEquals(RentalStatus.AVAILABLE, updatedItems.getStatus());

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertTrue(customerService.getReservationOf(updatedCustomer).isEmpty());
    }

    @Test
    public void testReportDefectiveItems() {
        PartySupplies items = new PartySupplies("Lautsprecher", 100, RentalStatus.AVAILABLE);
        partySuppliesRepository.save(items);

        partyService.reportDefectiveItems(items);

        assertEquals(RentalStatus.DEFECTIVE, items.getStatus());
    }

    @Test
    void addSuppliesSuccess() {
        PartySupplies supplies = new PartySupplies("Drink", 10, RentalStatus.AVAILABLE);

        partyService.addSupplies(supplies);

        List<PartySupplies> itemList = partySuppliesRepository.findAll();
        Assertions.assertThat(itemList).hasSize(1);

        PartySupplies item = itemList.get(0);
        assertThat(item.getItemName()).isEqualTo(supplies.getItemName());
        assertThat(item.getQuantity()).isEqualTo(supplies.getQuantity());
        assertThat(item.getStatus()).isEqualTo(RentalStatus.AVAILABLE);
    }

    @Test
    void removeSuppliesSuccess() {
        PartySupplies items = new PartySupplies("Drink", 10, RentalStatus.AVAILABLE);
        partyService.addSupplies(items);

        partyService.removeSupplies(items);

        List<PartySupplies> itemList = partySuppliesRepository.findAll();
        assertThat(itemList).isEmpty();
    }

    @Test
    public void testMarkAsRepaired() {
        PartySupplies items = new PartySupplies("Lautsprecher", 100, RentalStatus.DEFECTIVE);
        partySuppliesRepository.save(items);

        partyService.markAsRepaired(items);

        assertEquals(RentalStatus.REPAIRED, items.getStatus());
    }
}
