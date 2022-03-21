import junit.framework.TestCase;
import model.Ticket;
import service.CalculatePercentileAndAverage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatePercentileAndAverageTest extends TestCase {

        List<Ticket> tickets;

    @Override
    protected void setUp() throws Exception {
        tickets = new ArrayList<>();

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
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "3:10",
                "19.03.22",
                "11:25",
                "SU17",
                1,
                21400
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "7:15",
                "19.03.22",
                "17:45",
                "B99",
                3,
                21400
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "9:00",
                "19.03.22",
                "19:30",
                "B1",
                4,
                21400
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "14:20",
                "19.03.22",
                "23:20",
                "B717",
                3,
                21400
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "6:00",
                "19.03.22",
                "13:30",
                "B17",
                2,
                21400
        ));
        tickets.add(new Ticket(
                "MSC",
                "Москва",
                "LDN",
                "Лондон",
                "19.03.22",
                "4:20",
                "19.03.22",
                "10:30",
                "S17",
                0,
                21400
        ));
    }

    public void testGetPercentile(){
        double[] array = tickets
                .stream()
                .map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .toArray();

        int index = (int) Math.ceil(90 / 100.0 * array.length);
        Arrays.sort(array);

        Double actual = CalculatePercentileAndAverage.getPercentile(tickets, 90);
        Double excepted = array[index];

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
