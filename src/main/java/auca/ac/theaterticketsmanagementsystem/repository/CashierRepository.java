
package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.theaterticketsmanagementsystem.model.Cashier;

public interface CashierRepository extends JpaRepository<Cashier, UUID> {

}
