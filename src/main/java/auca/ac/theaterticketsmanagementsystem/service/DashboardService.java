
package auca.ac.theaterticketsmanagementsystem.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.theaterticketsmanagementsystem.repository.CashierRepository;
import auca.ac.theaterticketsmanagementsystem.repository.PatronRepository;
import auca.ac.theaterticketsmanagementsystem.repository.TicketBookingRepository;
import auca.ac.theaterticketsmanagementsystem.repository.TicketRepository;

@Service
public class DashboardService {

    @Autowired
    private CashierRepository cashierRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Map<String, Long> getDashboardSummary() {

        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("totalCashiers", cashierRepository.count());
        summary.put("totalPatrons", patronRepository.count());
        summary.put("totalTicketBookings", ticketBookingRepository.count());
        summary.put("totalTickets", ticketRepository.count());

        return summary;
    }
}
