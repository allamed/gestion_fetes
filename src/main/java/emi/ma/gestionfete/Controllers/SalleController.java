package emi.ma.gestionfete.Controllers;

import emi.ma.gestionfete.models.Mariage;
import emi.ma.gestionfete.models.Salle;
import emi.ma.gestionfete.repositories.MariageRepository;
import emi.ma.gestionfete.repositories.SalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@AllArgsConstructor
public class SalleController {

    private SalleRepository salleRepository;
    @GetMapping(path="/salles")
    @ResponseBody
    public List<Salle> salleList(){
        return salleRepository.findAll();
    }
}
