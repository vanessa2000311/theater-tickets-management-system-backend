
package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.theaterticketsmanagementsystem.model.BoxOfficeCounter;

public interface BoxOfficeCounterRepository extends JpaRepository<BoxOfficeCounter, UUID> {

}
