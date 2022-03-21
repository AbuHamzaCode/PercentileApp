package service;

import model.Ticket;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 *  Class for calculate percentile and average of flight time
 */
public class CalculatePercentileAndAverage {

    /**
     *   Take list of tickets, take distance in seconds into an array
     *   after sort, find array element and return 90th percentile of flight time
     * @param tickets list tickets
     * @param percentile num of percentile
     * @return 90-th percentile of flight time
     */
    public static double getPercentile(List<Ticket> tickets, double percentile) {
        double[] arraySec = tickets
                .stream()
                .map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .toArray();

        int index = (int) Math.ceil(percentile / 100.0 * arraySec.length);
        Arrays.sort(arraySec);

        return arraySec[index];
    }

    /**
     *  Convert seconds to hours
     * @param sec seconds into a double
     * @return time in string format
     */
    public static String time(double sec){
        long hour = (long) sec / 3600;
        long minute = (long) sec / 60 % 60;
        long second = (long) sec  % 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
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
