
package auca.ac.theaterticketsmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.model.BoxOfficeCounter;
import auca.ac.theaterticketsmanagementsystem.repository.BoxOfficeCounterRepository;

@Service
public class BoxOfficeCounterService {

    @Autowired
    private BoxOfficeCounterRepository boxOfficeCounterRepository;

    public String saveBoxOfficeCounter(BoxOfficeCounter boxOfficeCounter) {
        boxOfficeCounterRepository.save(boxOfficeCounter);
        return "Box office counter saved successfully";
    }

    public List<BoxOfficeCounter> getAllBoxOfficeCounters() {
        return boxOfficeCounterRepository.findAll();
    }

    public Optional<BoxOfficeCounter> getBoxOfficeCounterById(UUID id) {
        return boxOfficeCounterRepository.findById(id);
    }

    public String updateBoxOfficeCounter(UUID id, BoxOfficeCounter updatedCounter) {
        Optional<BoxOfficeCounter> existingCounter = boxOfficeCounterRepository.findById(id);

        if (existingCounter.isPresent()) {
            BoxOfficeCounter counter = existingCounter.get();

            counter.setCounterNumber(updatedCounter.getCounterNumber());
            counter.setName(updatedCounter.getName());

            boxOfficeCounterRepository.save(counter);

            return "Box office counter updated successfully";
        } else {
            return "The box office counter with that id does not exist";
        }
    }

    public String deleteBoxOfficeCounter(UUID id) {
        Optional<BoxOfficeCounter> existingCounter = boxOfficeCounterRepository.findById(id);

        if (existingCounter.isPresent()) {
            boxOfficeCounterRepository.deleteById(id);
            return "Box office counter deleted successfully";
        } else {
            return "The box office counter with that id does not exist";
        }
    }
}
