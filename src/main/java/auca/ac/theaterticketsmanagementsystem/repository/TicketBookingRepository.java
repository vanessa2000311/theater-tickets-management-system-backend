
package auca.ac.theaterticketsmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.theaterticketsmanagementsystem.model.BookingStatus;
import auca.ac.theaterticketsmanagementsystem.model.TicketBooking;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, UUID> {

    List<TicketBooking> findByStatusOrderByShowDateAsc(BookingStatus status);

    List<TicketBooking> findByShowDateBetweenOrderByShowDateAsc(LocalDate start, LocalDate end);

    boolean existsByCashierIdAndShowDateAndStatusNot(
            UUID cashierId, LocalDate showDate, BookingStatus status);

}
