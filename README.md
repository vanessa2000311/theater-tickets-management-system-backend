# Theater Tickets Management System

## About My Project

This is my Theater Tickets Management System project that I developed using Java Spring Boot and PostgreSQL.

The main purpose of this project is to help manage theater ticket bookings, customers (patrons), cashiers, ticket classes, box office counters, and tickets.

I developed the backend using REST APIs, and I used Postman to test the different operations.

## Technologies I Used

- Java 17
- Spring Boot
- PostgreSQL
- Spring Data JPA
- Maven
- Postman
- Git and GitHub
- VS Code

## How I Worked on the Project

I developed this project step by step, starting from Part A up to Part F, including the Bonus Section.

### Part A — Basic CRUD

I started by creating the main entities, repositories, services, and controllers.

I implemented CRUD operations to save, view, update, and delete cashiers, patrons, box office counters, ticket classes, and ticket bookings.

### Part B — Filtering

In this part, I added methods to search patrons by their last names, filter ticket bookings by status, and find bookings between two dates.

### Part C — Advanced Queries

I used JPQL queries to find cashiers by ticket class, available box office counters, unused ticket classes, and patrons handled by a particular cashier.

### Part D — Business Rules

I added validations to prevent bookings with past dates and to avoid assigning a cashier to conflicting bookings on the same show date.

I also implemented booking status changes, ticket class assignments, and box office counter changes.

### Part E — Ticket Management

I created the Ticket entity and added methods to save, retrieve, search, and delete tickets.

I also made sure tickets could only be created for paid bookings and that a booking with tickets could not be deleted before its tickets were removed.

### Part F — Statistics

I added a query to count ticket bookings according to their statuses.

I also implemented a method to cancel bookings for a particular cashier on a selected date without cancelling paid bookings.

### Bonus Section

I added a dashboard that shows the total number of cashiers, patrons, ticket bookings, and tickets.

I also implemented pagination and sorting so that ticket bookings can be displayed in smaller groups and arranged by show date.

## Database

I used PostgreSQL to store the project data.

Database name: `theater_tickets_db`

Server port: `8083`

I configured the database connection in `application.properties`.

## How to Run My Project

First, create the PostgreSQL database named `theater_tickets_db` and configure the database username and password in `src/main/resources/application.properties`.

Then open the project in VS Code and run:

```powershell
mvn clean compile
```

After successful compilation, start the application using:

```powershell
mvn spring-boot:run "-Dspring-boot.run.main-class=auca.ac.theaterticketsmanagementsystem.TheaterTicketsManagementSystemApplication"
```

When the application starts, the APIs can be tested using Postman at:

`http://localhost:8083`

## Some API Endpoints I Tested

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/cashiers/all` | View all cashiers |
| GET | `/api/patrons/all` | View all patrons |
| POST | `/api/ticket-bookings/save` | Create a booking |
| GET | `/api/ticket-bookings/by-status?status=RESERVED` | Filter bookings |
| GET | `/api/box-office-counters/free` | View free counters |
| GET | `/api/tickets/all` | View tickets |
| GET | `/api/ticket-bookings/stats/by-status` | View booking statistics |
| GET | `/api/dashboard/summary` | View dashboard totals |
| GET | `/api/ticket-bookings/page?page=0&size=5&sort=showDate,desc` | View paginated bookings |

## Testing

I used Postman to test my API endpoints after implementing each part.

I tested the CRUD operations, filtering queries, business rules, ticket management, statistics, and bonus features.

I checked the HTTP responses, including successful requests and validation errors, to confirm that the endpoints were behaving as expected.

I also used `mvn clean compile` to check that my Java code compiled successfully.

## Challenges I Faced

While working on this project, I faced some challenges with running the Spring Boot application, testing API requests, and managing my project files.

I solved these problems by checking terminal error messages, correcting the affected files, and testing the requests again in Postman.

I also learned how to use Git commands to commit and push my work to GitHub after completing each part.

## What I Learned

Through this project, I improved my understanding of Spring Boot, REST APIs, PostgreSQL, JPA repositories, and JPQL queries.

I also learned how to implement validation rules, work with database relationships, test APIs using Postman, and organize a Java project into entities, repositories, services, and controllers.

## Conclusion

This project helped me practise backend development using Java Spring Boot and PostgreSQL.

I was able to implement the required features from Part A to Part F, including the Bonus Section, and test the system using Postman.