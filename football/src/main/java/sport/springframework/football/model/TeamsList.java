package sport.springframework.football.model;

import java.util.*;

public class TeamsList {

    Set<String> setWithTeams = new TreeSet<>();
    JsonReader reader = new JsonReader();

    public Set<String> getSetWithTeams() {
        return setWithTeams;
    }

    public void setSetWithTeams(Set<String> setWithTeams) {
        this.setWithTeams = setWithTeams;
    }

    public TeamsList() {
        getTeamsNames();
    }

    private void getTeamsNames(){
        Map<String, List<String>> matchesMapInfoForTeams = reader.getMatchesMapInfo();
        for (Map.Entry<String, List<String>> entry : matchesMapInfoForTeams.entrySet()) {
            setWithTeams.add(entry.getValue().get(1));
            setWithTeams.add(entry.getValue().get(3));
        }
    }
}
