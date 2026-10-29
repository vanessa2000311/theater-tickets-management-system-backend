package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.theaterticketsmanagementsystem.model.Cashier;

public interface CashierRepository extends JpaRepository<Cashier, UUID> {

    @Query("SELECT DISTINCT c FROM Cashier c JOIN c.ticketClasses t WHERE LOWER(t.name) = LOWER(:name)")
    List<Cashier> findByTicketClassName(@Param("name") String name);

}
