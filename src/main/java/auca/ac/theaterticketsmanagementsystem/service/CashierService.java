
package auca.ac.theaterticketsmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.model.BoxOfficeCounter;
import auca.ac.theaterticketsmanagementsystem.model.Cashier;
import auca.ac.theaterticketsmanagementsystem.model.TicketClass;
import auca.ac.theaterticketsmanagementsystem.repository.BoxOfficeCounterRepository;
import auca.ac.theaterticketsmanagementsystem.repository.CashierRepository;
import auca.ac.theaterticketsmanagementsystem.repository.TicketClassRepository;

@Service
public class CashierService {

    @Autowired
    private CashierRepository cashierRepository;

    @Autowired
    private TicketClassRepository ticketClassRepository;

    @Autowired
    private BoxOfficeCounterRepository boxOfficeCounterRepository;

    public String saveCashier(Cashier cashier) {
        cashierRepository.save(cashier);
        return "Cashier saved successfully";
    }

    public List<Cashier> getAllCashiers() {
        return cashierRepository.findAll();
    }

    public List<Cashier> getCashiersByTicketClass(String name) {
        return cashierRepository.findByTicketClassName(name);
    }

    public Optional<Cashier> getCashierById(UUID id) {
        return cashierRepository.findById(id);
    }

    public String addTicketClassToCashier(UUID cashierId, UUID ticketClassId) {
        Optional<Cashier> existingCashier = cashierRepository.findById(cashierId);

        if (existingCashier.isEmpty()) {
            return "The cashier with that id does not exist";
        }

        Optional<TicketClass> existingTicketClass = ticketClassRepository.findById(ticketClassId);

        if (existingTicketClass.isEmpty()) {
            return "That ticket class does not exist";
        }

        Cashier cashier = existingCashier.get();
        TicketClass ticketClass = existingTicketClass.get();

        for (TicketClass assignedClass : cashier.getTicketClasses()) {
            if (assignedClass.getId().equals(ticketClassId)) {
                return "The cashier already has that ticket class";
            }
        }

        cashier.getTicketClasses().add(ticketClass);
        cashierRepository.save(cashier);

        return "Ticket class added to the cashier";
    }

    public String changeCashierBoxOfficeCounter(UUID cashierId, int counterNumber) {
        Optional<Cashier> existingCashier = cashierRepository.findById(cashierId);

        if (existingCashier.isEmpty()) {
            return "The cashier with that id does not exist";
        }

        Optional<BoxOfficeCounter> existingCounter =
                boxOfficeCounterRepository.findByCounterNumber(counterNumber);

        if (existingCounter.isEmpty()) {
            return "That box office counter does not exist";
        }

        Cashier cashier = existingCashier.get();
        BoxOfficeCounter counter = existingCounter.get();

        boolean counterTaken = cashierRepository
                .existsByBoxOfficeCounterIdAndIdNot(counter.getId(), cashierId);

        if (counterTaken) {
            return "That box office counter is taken by another cashier";
        }

        cashier.setBoxOfficeCounter(counter);
        cashierRepository.save(cashier);

        return "Box office counter changed successfully";
    }

    public String updateCashier(UUID id, Cashier updatedCashier) {
        Optional<Cashier> existingCashier = cashierRepository.findById(id);

        if (existingCashier.isPresent()) {
            Cashier cashier = existingCashier.get();

            cashier.setFirstName(updatedCashier.getFirstName());
            cashier.setLastName(updatedCashier.getLastName());
            cashier.setPhone(updatedCashier.getPhone());

            cashierRepository.save(cashier);

            return "Cashier updated successfully";
        } else {
            return "The cashier with that id does not exist";
        }
    }

    public String deleteCashier(UUID id) {
        Optional<Cashier> existingCashier = cashierRepository.findById(id);

        if (existingCashier.isPresent()) {
            cashierRepository.deleteById(id);
            return "Cashier deleted successfully";
        } else {
            return "The cashier with that id does not exist";
        }
    }
}
