
package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.theaterticketsmanagementsystem.model.Patron;

public interface PatronRepository extends JpaRepository<Patron, UUID> {

    List<Patron> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT p FROM TicketBooking b JOIN b.patron p WHERE b.cashier.id = :cashierId")
    List<Patron> findPatronsByCashierId(@Param("cashierId") UUID cashierId);

}
