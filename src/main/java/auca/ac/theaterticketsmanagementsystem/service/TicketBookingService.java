
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
