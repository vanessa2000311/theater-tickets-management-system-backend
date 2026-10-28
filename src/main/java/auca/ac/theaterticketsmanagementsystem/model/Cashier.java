
package auca.ac.theaterticketsmanagementsystem.model;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

@Entity
@Table(name = "cashier")
public class Cashier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "box_office_counter_id")
    private BoxOfficeCounter boxOfficeCounter;

    @ManyToMany
    @JoinTable(
        name = "cashier_ticket_class",
        joinColumns = @JoinColumn(name = "cashier_id"),
        inverseJoinColumns = @JoinColumn(name = "ticket_class_id")
    )
    private List<TicketClass> ticketClasses = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BoxOfficeCounter getBoxOfficeCounter() {
        return boxOfficeCounter;
    }

    public void setBoxOfficeCounter(BoxOfficeCounter boxOfficeCounter) {
        this.boxOfficeCounter = boxOfficeCounter;
    }

    public List<TicketClass> getTicketClasses() {
        return ticketClasses;
    }

    public void setTicketClasses(List<TicketClass> ticketClasses) {
        this.ticketClasses = ticketClasses;
    }
}
