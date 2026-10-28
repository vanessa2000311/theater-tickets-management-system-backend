
package auca.ac.theaterticketsmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.model.Patron;
import auca.ac.theaterticketsmanagementsystem.repository.PatronRepository;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public String savePatron(Patron patron) {
        patronRepository.save(patron);
        return "Patron saved successfully";
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(UUID id) {
        return patronRepository.findById(id);
    }

    public String updatePatron(UUID id, Patron updatedPatron) {
        Optional<Patron> existingPatron = patronRepository.findById(id);

        if (existingPatron.isPresent()) {
            Patron patron = existingPatron.get();

            patron.setFirstName(updatedPatron.getFirstName());
            patron.setLastName(updatedPatron.getLastName());
            patron.setPatronNumber(updatedPatron.getPatronNumber());
            patron.setPhone(updatedPatron.getPhone());

            patronRepository.save(patron);

            return "Patron updated successfully";
        } else {
            return "The patron with that id does not exist";
        }
    }

    public String deletePatron(UUID id) {
        Optional<Patron> existingPatron = patronRepository.findById(id);

        if (existingPatron.isPresent()) {
            patronRepository.deleteById(id);
            return "Patron deleted successfully";
        } else {
            return "The patron with that id does not exist";
        }
    }
}
