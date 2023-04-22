package emi.ma.gestionfete.Controllers;

import emi.ma.gestionfete.models.Mariage;
import emi.ma.gestionfete.models.Personne;
import emi.ma.gestionfete.repositories.MariageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class MarriageController {
    private MariageRepository mariageRepository;

    @GetMapping(path="/marriages")
    @ResponseBody
    public List<Mariage> marriagesList(){

        return mariageRepository.findAll();
    }
}
