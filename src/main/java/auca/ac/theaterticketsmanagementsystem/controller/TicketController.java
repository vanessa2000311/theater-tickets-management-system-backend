
package auca.ac.theaterticketsmanagementsystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.theaterticketsmanagementsystem.model.Ticket;
import auca.ac.theaterticketsmanagementsystem.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/save")
    public ResponseEntity<String> saveTicket(
            @RequestBody Ticket ticket,
            @RequestParam UUID ticketBookingId) {

        String result = ticketService.saveTicket(ticket, ticketBookingId);

        if (result.equals("The ticket booking with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("Tickets are only for PAID ticket bookings")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable UUID id) {

        Optional<Ticket> ticket = ticketService.getTicketById(id);

        if (ticket.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The ticket with that id does not exist");
        }

        return ResponseEntity.ok(ticket.get());
    }

    @GetMapping("/patron/{patronNumber}")
    public List<Ticket> getTicketsByPatronNumber(
            @PathVariable String patronNumber) {

        return ticketService.getTicketsByPatronNumber(patronNumber);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable UUID id) {

        String result = ticketService.deleteTicket(id);

        if (result.equals("The ticket with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }
}
