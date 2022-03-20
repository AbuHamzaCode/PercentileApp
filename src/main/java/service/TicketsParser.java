package service;

import model.Ticket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class parse json file and create a list of tickets
 */
public class TicketsParser {


    /**
     *   Accept pathname and use method getTickets() where return JSONArray.
     *   And create a list of tickets
     * @param pathname json pathname
     * @return list of tickets
     */
    public static List<Ticket> createTickets(String pathname){
        JSONArray ticketsArray = getTickets(pathname);
        List<Ticket> tickets = new ArrayList<>();
        for (Object ticketObject : ticketsArray){
            JSONObject ticketJsonObject = (JSONObject) ticketObject;
            tickets.add(new Ticket(
                    (String) ticketJsonObject.get("origin"),
                    (String) ticketJsonObject.get("origin_name"),
                    (String) ticketJsonObject.get("destination"),
                    (String) ticketJsonObject.get("destination_name"),
                    (String) ticketJsonObject.get("departure_date"),
                    (String) ticketJsonObject.get("departure_time"),
                    (String) ticketJsonObject.get("arrival_date"),
                    (String) ticketJsonObject.get("arrival_time"),
                    (String) ticketJsonObject.get("carrier"),
                    ((Long) ticketJsonObject.get("stops")).intValue(),
                    ((Long) ticketJsonObject.get("price")).intValue()
            ));
        }
        return tickets;
    }

    /**
     *  This method use in createTickets() method.
     *  Parse json file and take there tickets in JSONArray format
     * @param pathname pathname
     * @return  JSONArray tickets
     */
    private static JSONArray getTickets(String pathname){
        JSONParser parser = new JSONParser();
        StringBuilder builder = new StringBuilder();
        JSONArray ticketsArray = null;
        try {
            List<String> list = Files.readAllLines(Paths.get(pathname));
            list.forEach(line -> builder.append(line));

            JSONObject jsonData = (JSONObject) parser.parse(builder.toString());
            ticketsArray = (JSONArray) jsonData.get("tickets");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketsArray;
    }

}
