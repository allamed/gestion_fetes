package emi.ma.gestionfete.Controllers;

import emi.ma.gestionfete.models.Invite;
import emi.ma.gestionfete.models.Mariage;
import emi.ma.gestionfete.models.Personne;
import emi.ma.gestionfete.repositories.InviteRepository;
import emi.ma.gestionfete.repositories.MariageRepository;
import emi.ma.gestionfete.repositories.PersonneRepository;
import javax.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class InviteController {

    private InviteRepository inviteRepository;
    private final MariageRepository mariageRepository;
    private final PersonneRepository personneRepository;

    @GetMapping(path="/invites")
    @ResponseBody
    public List<Invite> invitesList(){

        return inviteRepository.findAll();
    }


    @GetMapping(path = "/ListInvites")
    public String patients(Model model, @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "2") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword){
        Page<Invite> pageInvites=inviteRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listInvites",pageInvites.getContent());
        model.addAttribute("pages",new int[pageInvites.getTotalPages()]);
        System.out.println(pageInvites.getTotalPages());
        model.addAttribute("currentPage",page);
        return "invites";
    }




    @GetMapping("/{id}")
    public String getDetail(Model model, @PathVariable("id") String s) {
        model.addAttribute("invite", inviteRepository.findById(s).get());
        return "inviteDetail";
    }



    @GetMapping(path = "/add")
    public String formPatient(Model model){
        model.addAttribute("invite",new Invite());
        model.addAttribute("weddingChoiceList", mariageRepository.findAll());
        return "formInvite";
    }
    @PostMapping(path="/saveInvite")
    public String saveInvite(Invite invite ){
        inviteRepository.save(invite);
        System.out.println(invite.getNom());
        return "redirect:/ListInvites";
    }


    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String s) {
        model.addAttribute("invite", inviteRepository.findById(s).get());
        model.addAttribute("weddingChoiceList", mariageRepository.findAll());
        return "inviteEdit";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String s) {
        inviteRepository.deleteById(s);
        return "redirect:/ListInvites";
    }



    // avec posMan
    @PostMapping("/invites")
    @ResponseBody
    public Invite ajouterInviter(@RequestBody Invite invite){
        return  inviteRepository.save(invite);
    }
    @PutMapping("/invites/{id}")
    @ResponseBody
    public Invite Inviter(@RequestBody Invite invite ,@PathVariable("id") String cin){
        Invite invite2=inviteRepository.findByCin(cin);
        invite2=invite;
        return  inviteRepository.save(invite2);
    }

    @DeleteMapping("/invites/{id}")
    @ResponseBody
    public List<Invite> supprimerInvite(@PathVariable("id") String cin) {
        inviteRepository.deleteByCin(cin);
        return inviteRepository.findAll();
    }


}
