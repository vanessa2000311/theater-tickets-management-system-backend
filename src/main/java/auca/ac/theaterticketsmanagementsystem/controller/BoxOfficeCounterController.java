

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

import auca.ac.theaterticketsmanagementsystem.model.BoxOfficeCounter;
import auca.ac.theaterticketsmanagementsystem.service.BoxOfficeCounterService;

@RestController
@RequestMapping("/api/box-office-counters")
public class BoxOfficeCounterController {

    @Autowired
    private BoxOfficeCounterService boxOfficeCounterService;

    @PostMapping("/save")
    public ResponseEntity<String> saveBoxOfficeCounter(@RequestBody BoxOfficeCounter boxOfficeCounter) {
        String message = boxOfficeCounterService.saveBoxOfficeCounter(boxOfficeCounter);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BoxOfficeCounter>> getAllBoxOfficeCounters() {
        List<BoxOfficeCounter> counters = boxOfficeCounterService.getAllBoxOfficeCounters();
        return new ResponseEntity<>(counters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoxOfficeCounterById(@PathVariable UUID id) {
        Optional<BoxOfficeCounter> counter = boxOfficeCounterService.getBoxOfficeCounterById(id);

        if (counter.isPresent()) {
            return new ResponseEntity<>(counter.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The box office counter with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBoxOfficeCounter(@PathVariable UUID id, @RequestBody BoxOfficeCounter boxOfficeCounter) {
        String message = boxOfficeCounterService.updateBoxOfficeCounter(id, boxOfficeCounter);

        if (message.equals("The box office counter with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoxOfficeCounter(@PathVariable UUID id) {
        String message = boxOfficeCounterService.deleteBoxOfficeCounter(id);

        if (message.equals("The box office counter with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
