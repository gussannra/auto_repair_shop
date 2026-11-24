package se.yrgo.auto_repair_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.auto_repair_shop.domain.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Hämnta alla fordon som tillhör ett visst bilmärke
    List<Vehicle> findByBrand(String brand);

    // Hämta alla fordon för en viss kund baserat på kundens ID
    @Query("SELECT v FROM Vehicle v WHERE v.customer.id = :customerId")
    List<Vehicle> findVehiclesByCustomerId(@Param("customerId") Long customerId);

    // Uppdatera bilmärket för ett fordon baserat på fordonets ID
    @Modifying(
            clearAutomatically = true,
            flushAutomatically = true
    )
    @Transactional
    @Query("UPDATE Vehicle v SET v.brand = :brand WHERE v.id = :id")
    int updateBrandById(@Param("id") Long id, @Param("brand") String brand);

    // Lista bilmärke och registreringsnummer
    @Query("SELECT v.brand AS brand, v.registrationNumber AS registrationNumber FROM Vehicle v")
    List<VehicleBrandProjection> findAllVehiclesBrandAndRegNumber();
}
