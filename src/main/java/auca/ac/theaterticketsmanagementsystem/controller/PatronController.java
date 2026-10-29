
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

import auca.ac.theaterticketsmanagementsystem.model.Patron;
import auca.ac.theaterticketsmanagementsystem.service.PatronService;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @PostMapping("/save")
    public ResponseEntity<String> savePatron(@RequestBody Patron patron) {
        String message = patronService.savePatron(patron);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Patron>> getPatronsByLastName(@RequestParam String lastName) {
        List<Patron> patrons = patronService.getPatronsByLastName(lastName);
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @GetMapping("/of-cashier/{cashierId}")
    public ResponseEntity<?> getPatronsByCashierId(@PathVariable UUID cashierId) {
        if (!patronService.cashierExists(cashierId)) {
            return new ResponseEntity<>("The cashier with that id does not exist", HttpStatus.NOT_FOUND);
        } else {
            List<Patron> patrons = patronService.getPatronsByCashierId(cashierId);
            return new ResponseEntity<>(patrons, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable UUID id) {
        Optional<Patron> patron = patronService.getPatronById(id);

        if (patron.isPresent()) {
            return new ResponseEntity<>(patron.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The patron with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatron(@PathVariable UUID id, @RequestBody Patron patron) {
        String message = patronService.updatePatron(id, patron);

        if (message.equals("The patron with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable UUID id) {
        String message = patronService.deletePatron(id);

        if (message.equals("The patron with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
