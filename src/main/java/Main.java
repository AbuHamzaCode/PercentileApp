
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        System.out.println("Average flight time between Vladivostok and Tel aviv -> " +
                CalculatePercentileAndAverage.getAverage(tickets));
        System.out.println("90-th percentile of flight time between Vladivostok and Tel aviv -> " +
                CalculatePercentileAndAverage.getPercentile(tickets));
    }
}
