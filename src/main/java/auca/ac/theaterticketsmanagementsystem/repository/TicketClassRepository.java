
package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.theaterticketsmanagementsystem.model.TicketClass;

public interface TicketClassRepository extends JpaRepository<TicketClass, UUID> {

}
