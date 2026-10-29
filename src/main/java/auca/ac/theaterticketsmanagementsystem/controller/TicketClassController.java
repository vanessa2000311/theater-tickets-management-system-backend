
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

import auca.ac.theaterticketsmanagementsystem.model.TicketClass;
import auca.ac.theaterticketsmanagementsystem.service.TicketClassService;

@RestController
@RequestMapping("/api/ticket-classes")
public class TicketClassController {

    @Autowired
    private TicketClassService ticketClassService;

    @PostMapping("/save")
    public ResponseEntity<String> saveTicketClass(@RequestBody TicketClass ticketClass) {
        String message = ticketClassService.saveTicketClass(ticketClass);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketClass>> getAllTicketClasses() {
        List<TicketClass> ticketClasses = ticketClassService.getAllTicketClasses();
        return new ResponseEntity<>(ticketClasses, HttpStatus.OK);
    }

    @GetMapping("/unused")
    public ResponseEntity<List<TicketClass>> getUnusedTicketClasses() {
        List<TicketClass> ticketClasses = ticketClassService.getUnusedTicketClasses();
        return new ResponseEntity<>(ticketClasses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketClassById(@PathVariable UUID id) {
        Optional<TicketClass> ticketClass = ticketClassService.getTicketClassById(id);

        if (ticketClass.isPresent()) {
            return new ResponseEntity<>(ticketClass.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The ticket class with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTicketClass(@PathVariable UUID id, @RequestBody TicketClass ticketClass) {
        String message = ticketClassService.updateTicketClass(id, ticketClass);

        if (message.equals("The ticket class with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicketClass(@PathVariable UUID id) {
        String message = ticketClassService.deleteTicketClass(id);

        if (message.equals("The ticket class with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
