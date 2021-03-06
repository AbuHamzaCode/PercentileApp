package main;

import model.Ticket;
import service.CalculatePercentileAndAverage;
import service.TicketsParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "";
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter file path:");
            filePath = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Ticket> tickets = TicketsParser.createTickets(filePath);

        double flightTime = CalculatePercentileAndAverage.getPercentile(tickets, 90);

        System.out.println("Average flight time between Vladivostok and Tel aviv -> " +
                CalculatePercentileAndAverage.getAverage(tickets));

        System.out.print("90-th percentile of flight time between Vladivostok and Tel aviv: " + "\n");
        System.out.println("Time format -> " + CalculatePercentileAndAverage.time(flightTime));
        System.out.println("Duration format -> " + Duration.ofSeconds((long) flightTime));
    }
}
