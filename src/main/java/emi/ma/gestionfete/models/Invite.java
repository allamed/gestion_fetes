package emi.ma.gestionfete.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invite extends Personne{

    @Enumerated(EnumType.STRING)
    @ToString.Include
    private StatusInvite statut;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Mariage> marriages = new HashSet<>();


    public Invite(String cin, String nom, String prenom, Genre genre, StatusInvite statut) {
        super(cin,nom,prenom,genre);
        this.statut=statut;
    }
}

