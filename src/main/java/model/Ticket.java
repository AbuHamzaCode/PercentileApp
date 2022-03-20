package model;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Lombok
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String carrier;
    private int stops;
    private int price;

    public Duration getDuration() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

        LocalDateTime departure = LocalDateTime.parse(departureDate + " " + departureTime, formatter);
        LocalDateTime arrival = LocalDateTime.parse(arrivalDate + " " + arrivalTime, formatter);

        return Duration.between(departure, arrival);
    }
}
