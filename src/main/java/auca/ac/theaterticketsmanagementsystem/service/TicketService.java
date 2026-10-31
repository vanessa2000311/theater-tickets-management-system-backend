
package auca.ac.theaterticketsmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.model.BookingStatus;
import auca.ac.theaterticketsmanagementsystem.model.Ticket;
import auca.ac.theaterticketsmanagementsystem.model.TicketBooking;
import auca.ac.theaterticketsmanagementsystem.repository.TicketRepository;
import auca.ac.theaterticketsmanagementsystem.repository.TicketBookingRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    public String saveTicket(Ticket ticket, UUID ticketBookingId) {

        Optional<TicketBooking> existingBooking =
                ticketBookingRepository.findById(ticketBookingId);

        if (existingBooking.isEmpty()) {
            return "The ticket booking with that id does not exist";
        }

        TicketBooking booking = existingBooking.get();

        if (booking.getStatus() != BookingStatus.PAID) {
            return "Tickets are only for PAID ticket bookings";
        }

        ticket.setTicketBooking(booking);
        ticket.setIssuedDate(LocalDate.now());

        ticketRepository.save(ticket);

        return "Ticket saved successfully";
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(UUID id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getTicketsByPatronNumber(String patronNumber) {
        return ticketRepository.findTicketsByPatronNumber(patronNumber);
    }

    public String deleteTicket(UUID id) {

        Optional<Ticket> existingTicket = ticketRepository.findById(id);

        if (existingTicket.isEmpty()) {
            return "The ticket with that id does not exist";
        }

        ticketRepository.deleteById(id);

        return "Ticket deleted successfully";
    }
}
