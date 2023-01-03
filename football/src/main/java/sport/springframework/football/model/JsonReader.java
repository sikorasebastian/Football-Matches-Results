package sport.springframework.football.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonReader{
    private List<String> matchesIDList = new ArrayList<>();
    private List<String> displayedMatches = new ArrayList<>();

    private int numberMatches = 10;

    public List<String> getDisplayedMatches() {
        return displayedMatches;
    }

    public void setDisplayedMatches(List<String> displayedMatches) {
        this.displayedMatches = displayedMatches;
    }

    public int getNumberMatches() {
        return numberMatches;
    }

    public void setNumberMatches(int numberMatches) {
        this.numberMatches = numberMatches;
    }

    public List<String> getMatchesIDList() {
        return matchesIDList;
    }

    public JsonReader(){
        readFromJson();
    }

    public void readFromJson(){
        JSONParser parser = new JSONParser();

        try {
            JSONObject obj  =(JSONObject) parser.parse(new FileReader("src/main/resources/data.json"));

            JSONArray matchesArray = (JSONArray) obj.get("Events");
            int i = 0;
            for (Object match : matchesArray)
            {
                JSONObject sportEvent = (JSONObject) match;

                String id = (String) sportEvent.get("sport_event_id");
                System.out.println("id:"+ id);
                matchesIDList.add(id);
                if (i < numberMatches){
                    displayedMatches.add(id);
                }

                String date = (String) sportEvent.get("start_date");
                System.out.println("date:"+ date);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
