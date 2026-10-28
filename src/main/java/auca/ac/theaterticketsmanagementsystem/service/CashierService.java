
package auca.ac.theaterticketsmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.model.Cashier;
import auca.ac.theaterticketsmanagementsystem.repository.CashierRepository;

@Service
public class CashierService {

    @Autowired
    private CashierRepository cashierRepository;

    public String saveCashier(Cashier cashier) {
        cashierRepository.save(cashier);
        return "Cashier saved successfully";
    }

    public List<Cashier> getAllCashiers() {
        return cashierRepository.findAll();
    }

    public Optional<Cashier> getCashierById(UUID id) {
        return cashierRepository.findById(id);
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
