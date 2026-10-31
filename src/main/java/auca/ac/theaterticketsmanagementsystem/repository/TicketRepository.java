
package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.theaterticketsmanagementsystem.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    @Query("SELECT t FROM Ticket t WHERE t.ticketBooking.patron.patronNumber = :patronNumber ORDER BY t.issuedDate DESC")
    List<Ticket> findTicketsByPatronNumber(@Param("patronNumber") String patronNumber);

    boolean existsByTicketBookingId(UUID ticketBookingId);
}
