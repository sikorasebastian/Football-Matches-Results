package sport.springframework.football.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sport.springframework.football.model.JsonReader;

@Controller
public class ResultController {

    @RequestMapping("/matches")
    public String getAuthors(Model model){
        JsonReader reader = new JsonReader();
        model.addAttribute("json", reader);
        return "matches/results";

    }
}
