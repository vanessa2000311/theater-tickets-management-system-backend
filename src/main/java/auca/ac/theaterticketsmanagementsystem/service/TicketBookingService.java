
package auca.ac.theaterticketsmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.model.BookingStatus;
import auca.ac.theaterticketsmanagementsystem.model.TicketBooking;
import auca.ac.theaterticketsmanagementsystem.repository.TicketBookingRepository;

@Service
public class TicketBookingService {

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    public String saveTicketBooking(TicketBooking ticketBooking) {

        if (ticketBooking.getShowDate() == null ||
                ticketBooking.getShowDate().isBefore(LocalDate.now())) {
            return "Ticket booking date cannot be in the past";
        }

        if (ticketBooking.getCashier() != null &&
                ticketBooking.getCashier().getId() != null) {

            boolean alreadyBooked = ticketBookingRepository
                    .existsByCashierIdAndShowDateAndStatusNot(
                            ticketBooking.getCashier().getId(),
                            ticketBooking.getShowDate(),
                            BookingStatus.CANCELLED);

            if (alreadyBooked) {
                return "Cashier already processes a booking for that show date";
            }
        }

        ticketBooking.setStatus(BookingStatus.RESERVED);
        ticketBookingRepository.save(ticketBooking);

        return "Ticket booking saved successfully";
    }

    public List<TicketBooking> getAllTicketBookings() {
        return ticketBookingRepository.findAll();
    }

    public Optional<TicketBooking> getTicketBookingById(UUID id) {
        return ticketBookingRepository.findById(id);
    }

    public List<TicketBooking> getTicketBookingsByStatus(BookingStatus status) {
        return ticketBookingRepository.findByStatusOrderByShowDateAsc(status);
    }

    public List<TicketBooking> getTicketBookingsBetweenDates(LocalDate start, LocalDate end) {
        return ticketBookingRepository.findByShowDateBetweenOrderByShowDateAsc(start, end);
    }

    public String changeTicketBookingStatus(UUID id, BookingStatus newStatus) {
        Optional<TicketBooking> existingBooking = ticketBookingRepository.findById(id);

        if (existingBooking.isEmpty()) {
            return "The ticket booking with that id does not exist";
        }

        TicketBooking booking = existingBooking.get();
        BookingStatus currentStatus = booking.getStatus();

        boolean allowed = false;

        if (currentStatus == BookingStatus.RESERVED) {
            if (newStatus == BookingStatus.CONFIRMED ||
                    newStatus == BookingStatus.CANCELLED) {
                allowed = true;
            }
        } else if (currentStatus == BookingStatus.CONFIRMED) {
            if (newStatus == BookingStatus.PAID ||
                    newStatus == BookingStatus.CANCELLED) {
                allowed = true;
            }
        }

        if (!allowed) {
            return "Cannot change status from " + currentStatus + " to " + newStatus;
        }

        booking.setStatus(newStatus);
        ticketBookingRepository.save(booking);

        return "Ticket booking status updated successfully";
    }

    public String updateTicketBooking(UUID id, TicketBooking updatedBooking) {
        Optional<TicketBooking> existingBooking = ticketBookingRepository.findById(id);

        if (existingBooking.isPresent()) {
            TicketBooking booking = existingBooking.get();

            booking.setShowDate(updatedBooking.getShowDate());
            booking.setShowTitle(updatedBooking.getShowTitle());
            booking.setSeats(updatedBooking.getSeats());
            booking.setPatron(updatedBooking.getPatron());
            booking.setCashier(updatedBooking.getCashier());

            ticketBookingRepository.save(booking);

            return "Ticket booking updated successfully";
        } else {
            return "The ticket booking with that id does not exist";
        }
    }

    public String deleteTicketBooking(UUID id) {
        Optional<TicketBooking> existingBooking = ticketBookingRepository.findById(id);

        if (existingBooking.isPresent()) {
            ticketBookingRepository.deleteById(id);
            return "Ticket booking deleted successfully";
        } else {
            return "The ticket booking with that id does not exist";
        }
    }
}
