package emi.ma.gestionfete.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {


    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
