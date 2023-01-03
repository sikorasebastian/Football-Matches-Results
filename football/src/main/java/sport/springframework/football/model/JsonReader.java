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

    public List<String> getMatchesIDList() {
        return matchesIDList;
    }
//private String[] matchesIDList = new String[73];
//
//    public String[] getMatchesIDList() {
//        return matchesIDList;
//    }

    public JsonReader(){
        readFromJson();
    }

    public void readFromJson(){
        JSONParser parser = new JSONParser();

        try {
            JSONObject obj  =(JSONObject) parser.parse(new FileReader("src/main/resources/data.json"));
//            JSONArray matchesArray = new JSONArray();
//            matchesArray.add(obj);
//            System.out.println(matchesArray.size());
//            System.out.println(matchesArray.get(0));

            JSONArray matchesArray = (JSONArray) obj.get("Events");
           // int i = 0;
            for (Object match : matchesArray)
            {
                JSONObject sportEvent = (JSONObject) match;

                String id = (String) sportEvent.get("sport_event_id");
                System.out.println("id:"+ id);
                matchesIDList.add(id);

              //  matchesIDList[i] = id;
              //  i++;
                //System.out.println(matchesIDList.get(0));


                String date = (String) sportEvent.get("start_date");
                System.out.println("date:"+ date);

                // JSONArray cars = (JSONArray) sportEvent.get("cars");

//                for (Object c : cars)
//                {
//                    System.out.println(c+"");
//                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void run(String... args) throws Exception {
//        readFromJson();
//    }
}
