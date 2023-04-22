package emi.ma.gestionfete;

import emi.ma.gestionfete.models.*;
import emi.ma.gestionfete.repositories.InviteRepository;
import emi.ma.gestionfete.repositories.MariageRepository;
import emi.ma.gestionfete.repositories.PersonneRepository;
import emi.ma.gestionfete.repositories.SalleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class GestionFeteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionFeteApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonneRepository personneRepository,
                                        MariageRepository mariageRepository,
                                        InviteRepository inviteRepository,
                                        SalleRepository salleRepository){
        return args -> {
            System.out.println("**********************Debut *********************************");
            Personne p1=new Personne();
            p1.setCin("jhshchsclh");
            p1.setNom("elhasbi2");
            p1.setPrenom("ayat");
            p1.setGenre(Genre.Femme);
            personneRepository.save(p1);

            Personne p2=new Personne();
            p2.setCin("nmjkcp");
            p2.setNom("elhasbi");
            p2.setPrenom("ahmed");
            p2.setGenre(Genre.Homme);
            personneRepository.save(p2);

            personneRepository.findAll().forEach(personne -> System.out.println(personne.getNom()));


            Salle salle=new Salle();
            salle.setNom("cityCalifornie");
            salle.setAdresse("bouskoura");
            salle.setCapacite(200);
            salle.setVille("bouskoura");
            salleRepository.save(salle);

            salleRepository.findAll().forEach(sal -> System.out.println(sal.getNom()));

            Mariage m1=new Mariage(p2,p1,new Date());
            m1.setSalle(salle);
            m1.setNombreMaxInviteEpoux(60);
            m1.setNombreMinimumTemoins(2);

            Invite temoin1 =new Invite("hlffbblllhsohu","rachid","elyaghoudi",Genre.Homme,StatusInvite.Temoin);
            Invite temoin2 =new Invite("jhnuhicmocfgfyuluygil","rachida","elyaghoudi",Genre.Femme,StatusInvite.Temoin);
            Invite invite1 =new Invite("nkmnk,qk,kld,mDOJIJ",p1.getNom(),p1.getPrenom(),Genre.Femme,StatusInvite.InviteEpouse);
            Invite invite2 =new Invite("jjkmnvjnmknjm",p2.getNom(),p2.getPrenom(),Genre.Homme,StatusInvite.InviteEpoux);

            inviteRepository.save(temoin2);
            inviteRepository.save(temoin1);
            inviteRepository.save(invite1);
            inviteRepository.save(invite2);

            inviteRepository.findAll().forEach(invite -> System.out.println(invite.getNom()));

            m1.getListInvites().add(temoin1);
            m1.getListInvites().add(temoin2);
            m1.getListInvites().add(invite1);
            m1.getListInvites().add(invite2);

            mariageRepository.save(m1);

            mariageRepository.findAll().forEach(mr -> System.out.println(mr.getNum()));
            System.out.println("****************fin**************");


        };
    }

}
