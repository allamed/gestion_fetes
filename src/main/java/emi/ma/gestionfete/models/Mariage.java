package emi.ma.gestionfete.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @ToString
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name="nbr_min_temoins")
    private int nombreMinimumTemoins;

    @Column(name="cap_min")
    private int capaciteMinimal;

    @Column(name="nbr_max_invité")
    private int nombreMaxInviteEpoux;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Salle salle;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set <Invite> ListInvites = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    private Personne epouse;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)

    private Personne epoux;


    public Invite QRInvitation(StatusInvite statut,Personne p) {
        Invite i = new Invite(p.getCin(),p.getNom(),p.getPrenom(),p.getGenre(),statut);
        this.ListInvites.add(i);
        return(i);
    }

    public Mariage(Personne p1,Personne p2, Date date){
        this.setEpoux(p1);
        this.setEpouse(p2);
        this.setDate(date);
    }
}
