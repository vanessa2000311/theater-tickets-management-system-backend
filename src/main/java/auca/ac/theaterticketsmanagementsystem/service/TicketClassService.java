
package auca.ac.theaterticketsmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.model.TicketClass;
import auca.ac.theaterticketsmanagementsystem.repository.TicketClassRepository;

@Service
public class TicketClassService {

    @Autowired
    private TicketClassRepository ticketClassRepository;

    public String saveTicketClass(TicketClass ticketClass) {
        ticketClassRepository.save(ticketClass);
        return "Ticket class saved successfully";
    }

    public List<TicketClass> getAllTicketClasses() {
        return ticketClassRepository.findAll();
    }

    public List<TicketClass> getUnusedTicketClasses() {
        return ticketClassRepository.findUnusedTicketClasses();
    }

    public Optional<TicketClass> getTicketClassById(UUID id) {
        return ticketClassRepository.findById(id);
    }

    public String updateTicketClass(UUID id, TicketClass updatedTicketClass) {
        Optional<TicketClass> existingTicketClass = ticketClassRepository.findById(id);

        if (existingTicketClass.isPresent()) {
            TicketClass ticketClass = existingTicketClass.get();

            ticketClass.setName(updatedTicketClass.getName());

            ticketClassRepository.save(ticketClass);

            return "Ticket class updated successfully";
        } else {
            return "The ticket class with that id does not exist";
        }
    }

    public String deleteTicketClass(UUID id) {
        Optional<TicketClass> existingTicketClass = ticketClassRepository.findById(id);

        if (existingTicketClass.isPresent()) {
            ticketClassRepository.deleteById(id);
            return "Ticket class deleted successfully";
        } else {
            return "The ticket class with that id does not exist";
        }
    }
}
