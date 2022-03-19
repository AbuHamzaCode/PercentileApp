import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.time.Duration;
import java.util.List;

/**
 *  Class for calculate percentile and average of flight time
 */
public class CalculatePercentileAndAverage {

    /**
     *   Take list of tickets and using the percentile class
     *   after return 90th percentile of flight time
     * @param tickets list tickets
     * @return 90-th percentile of flight time
     */
    public static Duration getPercentile(List<Ticket> tickets) {
        double[] arraySec = tickets
                .stream()
                .map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .toArray();

        Percentile percentile = new Percentile();
        percentile.setData(arraySec);
        return Duration.ofSeconds((long) percentile.evaluate(90));
    }

    /**
     *   Take list of tickets, using java.time.Duration
     *   and return average flight time
     * @param tickets list tickets
     * @return Average flight time
     */
    public static Duration getAverage(List<Ticket> tickets) {
        return Duration.ofSeconds((long) tickets
                .stream()
                .map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .average()
                .getAsDouble());
    }
}
