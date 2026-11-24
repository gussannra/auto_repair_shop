package se.yrgo.auto_repair_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.yrgo.auto_repair_shop.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Hämta en kund baserat på namn
    Optional<Customer> findByName(String name);

    // Hämta alla kunder tillsammans med deras fordon
    @Query("SELECT c, v FROM Customer c LEFT JOIN Vehicle v ON v.customer = c")
    List<Object[]> findAllCustomerWithAllVehicles();
}
