
package auca.ac.theaterticketsmanagementsystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import auca.ac.theaterticketsmanagementsystem.model.BoxOfficeCounter;

public interface BoxOfficeCounterRepository extends JpaRepository<BoxOfficeCounter, UUID> {

    @Query("SELECT b FROM BoxOfficeCounter b WHERE NOT EXISTS (SELECT c FROM Cashier c WHERE c.boxOfficeCounter = b) ORDER BY b.counterNumber ASC")
    List<BoxOfficeCounter> findFreeBoxOfficeCounters();

    Optional<BoxOfficeCounter> findByCounterNumber(int counterNumber);

}
