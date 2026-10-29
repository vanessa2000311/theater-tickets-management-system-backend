
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.theaterticketsmanagementsystem.model.Cashier;
import auca.ac.theaterticketsmanagementsystem.service.CashierService;

@RestController
@RequestMapping("/api/cashiers")
public class CashierController {

    @Autowired
    private CashierService cashierService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCashier(@RequestBody Cashier cashier) {
        String message = cashierService.saveCashier(cashier);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cashier>> getAllCashiers() {
        List<Cashier> cashiers = cashierService.getAllCashiers();
        return new ResponseEntity<>(cashiers, HttpStatus.OK);
    }

    @GetMapping("/by-ticket-class")
    public ResponseEntity<List<Cashier>> getCashiersByTicketClass(@RequestParam String name) {
        List<Cashier> cashiers = cashierService.getCashiersByTicketClass(name);
        return new ResponseEntity<>(cashiers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCashierById(@PathVariable UUID id) {
        Optional<Cashier> cashier = cashierService.getCashierById(id);

        if (cashier.isPresent()) {
            return new ResponseEntity<>(cashier.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The cashier with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCashier(@PathVariable UUID id, @RequestBody Cashier cashier) {
        String message = cashierService.updateCashier(id, cashier);

        if (message.equals("The cashier with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCashier(@PathVariable UUID id) {
        String message = cashierService.deleteCashier(id);

        if (message.equals("The cashier with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}

