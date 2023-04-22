package emi.ma.gestionfete.Controllers;


import emi.ma.gestionfete.models.Personne;
import emi.ma.gestionfete.repositories.PersonneRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PersonneController {

    private PersonneRepository personneRepository;


    @GetMapping(path="/personnes")
    @ResponseBody
    public List<Personne> personnesList(){
        return personneRepository.findAll();
    }

    @GetMapping(path = "/ListPersonnes")
    public String patients(Model model, @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "2") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword){
        Page<Personne> pagepersonnes=personneRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listPersonnes",pagepersonnes.getContent());
        model.addAttribute("pages",new int[pagepersonnes.getTotalPages()]);
        System.out.println(pagepersonnes.getTotalPages());
        model.addAttribute("currentPage",page);
        return "personnes";
    }
}
