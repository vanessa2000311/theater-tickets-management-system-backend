
package auca.ac.theaterticketsmanagementsystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.theaterticketsmanagementsystem.model.TicketBooking;
import auca.ac.theaterticketsmanagementsystem.service.TicketBookingService;

@RestController
@RequestMapping("/api/ticket-bookings")
public class TicketBookingController {

    @Autowired
    private TicketBookingService ticketBookingService;

    @PostMapping("/save")
    public ResponseEntity<String> saveTicketBooking(@RequestBody TicketBooking ticketBooking) {
        String message = ticketBookingService.saveTicketBooking(ticketBooking);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketBooking>> getAllTicketBookings() {
        List<TicketBooking> bookings = ticketBookingService.getAllTicketBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketBookingById(@PathVariable UUID id) {
        Optional<TicketBooking> booking = ticketBookingService.getTicketBookingById(id);

        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The ticket booking with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTicketBooking(
            @PathVariable UUID id,
            @RequestBody TicketBooking ticketBooking) {

        String message = ticketBookingService.updateTicketBooking(id, ticketBooking);

        if (message.equals("The ticket booking with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicketBooking(@PathVariable UUID id) {
        String message = ticketBookingService.deleteTicketBooking(id);

        if (message.equals("The ticket booking with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
