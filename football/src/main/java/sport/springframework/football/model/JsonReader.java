package sport.springframework.football.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class JsonReader{
    private List<String> matchesIDList = new ArrayList<>();
    private List<String> displayedMatches = new ArrayList<>();
    private Map<String, List<String>> matchesMapInfo = new HashMap<>();
    private Map<String, List<String>> matchesMapTen = new HashMap<>();
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

    public Map<String, List<String>> getMatchesMapInfo() {
        return matchesMapInfo;
    }

    public void setMatchesMapInfo(Map<String, List<String>> matchesMapInfo) {
        this.matchesMapInfo = matchesMapInfo;
    }

    public Map<String, List<String>> getMatchesMapTen() {
        return matchesMapTen;
    }

    public void setMatchesMapTen(Map<String, List<String>> matchesMapTen) {
        this.matchesMapTen = matchesMapTen;
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
                List<String> dataList = new ArrayList<>();
                JSONObject sportEvent = (JSONObject) match;

                String id = (String) sportEvent.get("sport_event_id");
                matchesIDList.add(id);
                if (i < numberMatches){
                    displayedMatches.add(id);
                }

                String dateStr = (String) sportEvent.get("start_date");
                String date = dateStr.substring(0,10) + " " +dateStr.substring(11,19);
                dataList.add(date);

                JSONArray competitors = (JSONArray) sportEvent.get("competitors");
                for (Object competitor : competitors){
                    JSONObject team = (JSONObject) competitor;
                    String name = (String) team.get("name");
                    String country = (String) team.get("country");
                    dataList.add(name);
                    dataList.add(country);
                }

                JSONObject venue = (JSONObject) sportEvent.get("venue");
                if(venue == null){
                    dataList.add("Unknown");
                }
                else{
                    String venueName = (String) venue.get("name");
                    dataList.add(venueName);
                }
                double homeRate;
                double drawRate;
                double awayRate;
                if((sportEvent.get("probability_home_team_winner")) instanceof Long){
                    String homeRateStr = String.valueOf(sportEvent.get("probability_home_team_winner"));
                    homeRate = Double.parseDouble(homeRateStr);
                }
                else {
                     homeRate = (double) sportEvent.get("probability_home_team_winner");
                }

                if((sportEvent.get("probability_draw")) instanceof Long){
                    String drawRateStr = String.valueOf(sportEvent.get("probability_draw"));
                    drawRate = Double.parseDouble(drawRateStr);
                }
                else {
                    drawRate = (double) sportEvent.get("probability_draw");
                }

                if((sportEvent.get("probability_away_team_winner")) instanceof Long){
                    String awayRateStr = String.valueOf(sportEvent.get("probability_away_team_winner"));
                    awayRate = Double.parseDouble(awayRateStr);
                }
                else {
                    awayRate = (double) sportEvent.get("probability_away_team_winner");
                }

                Map<String, Double> ratesMap = new HashMap<>();
                ratesMap.put("HOME_TEAM_WIN", homeRate);
                ratesMap.put("DRAW", drawRate);
                ratesMap.put("AWAY_TEAM_WIN", awayRate);
                String theWinnerIs = Collections.max(ratesMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
                dataList.add(theWinnerIs);
                dataList.add(String.valueOf(ratesMap.get(theWinnerIs)));
                matchesMapInfo.put(id, dataList);
                if(i < numberMatches) {
                    matchesMapTen.put(id, dataList);
                }
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
