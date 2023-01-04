package sport.springframework.football.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sport.springframework.football.model.JsonReader;
import sport.springframework.football.model.TeamsList;

@Controller
public class ResultController {
    @RequestMapping("/matches")
    public String getAllMatches(Model model){
        JsonReader reader = new JsonReader();
        model.addAttribute("json", reader);
        return "matches/results";
    }

    @RequestMapping("/listOfAllTeams")
    public String getAllTeamsSorted(Model model){
        TeamsList teamList = new TeamsList();
        model.addAttribute("teamList", teamList.getSetWithTeams());
        return "matches/teams";
    }
}
