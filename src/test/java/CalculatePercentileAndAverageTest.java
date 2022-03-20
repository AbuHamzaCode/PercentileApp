import junit.framework.TestCase;
import model.Ticket;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import service.CalculatePercentileAndAverage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CalculatePercentileAndAverageTest extends TestCase {

        List<Ticket> tickets;
        Percentile percentile;

    @Override
    protected void setUp() throws Exception {
        tickets = new ArrayList<>();
        percentile = new Percentile();

        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "9:45",
                "19.03.22",
                "18:00",
                "B17",
                1,
                20500
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "11:50",
                "19.03.22",
                "16:40",
                "B717",
                0,
                22400
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "13:20",
                "19.03.22",
                "23:30",
                "SU202",
                2,
                19000
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "6:45",
                "19.03.22",
                "15:30",
                "B17",
                1,
                21400
        ));
    }

    public void testGetPercentile(){
        double[] array = tickets
                .stream()
                .map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .toArray();
        percentile.setData(array);

        Duration actual = CalculatePercentileAndAverage.getPercentile(tickets);
        Duration excepted = Duration.ofSeconds((long) percentile.evaluate(90));

        assertEquals(excepted, actual);
    }

    public void testGetAverage(){
        Duration actual = CalculatePercentileAndAverage.getAverage(tickets);
        Duration excepted = Duration.ofSeconds((long) tickets
                .stream()
                .map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .average()
                .getAsDouble());

        assertEquals(excepted, actual);
    }
}
