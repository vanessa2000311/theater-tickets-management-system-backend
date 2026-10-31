
package auca.ac.theaterticketsmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import auca.ac.theaterticketsmanagementsystem.model.BookingStatus;
import auca.ac.theaterticketsmanagementsystem.model.TicketBooking;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, UUID> {

    List<TicketBooking> findByStatusOrderByShowDateAsc(BookingStatus status);

    List<TicketBooking> findByShowDateBetweenOrderByShowDateAsc(LocalDate start, LocalDate end);

    boolean existsByCashierIdAndShowDateAndStatusNot(
            UUID cashierId, LocalDate showDate, BookingStatus status);

    @Query("SELECT b.status, COUNT(b) FROM TicketBooking b GROUP BY b.status")
    List<Object[]> countTicketBookingsByStatus();

    @Modifying
    @Transactional
    @Query("UPDATE TicketBooking b SET b.status = :cancelled WHERE b.cashier.id = :cashierId AND b.showDate = :date AND b.status <> :paid")
    int cancelBookingsByCashierAndDate(
            @Param("cashierId") UUID cashierId,
            @Param("date") LocalDate date,
            @Param("cancelled") BookingStatus cancelled,
            @Param("paid") BookingStatus paid);

}
