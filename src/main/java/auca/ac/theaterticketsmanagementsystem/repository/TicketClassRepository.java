
package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import auca.ac.theaterticketsmanagementsystem.model.TicketClass;

public interface TicketClassRepository extends JpaRepository<TicketClass, UUID> {

    @Query("SELECT t FROM TicketClass t WHERE t.cashiers IS EMPTY")
    List<TicketClass> findUnusedTicketClasses();

}
