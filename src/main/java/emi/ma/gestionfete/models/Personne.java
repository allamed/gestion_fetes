package emi.ma.gestionfete.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Personne {
    @Id
    private String cin;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "epoux")
    private Set<Mariage> marriagesHomme = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "epouse")
    private Set<Mariage> marriagesFemme = new HashSet<>();

    public Personne(String cin, String nom, String prenom, Genre genre) {
        this.cin=cin;
        this.genre=genre;
        this.nom=nom;
        this.prenom=prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Personne person = (Personne) o;
        return cin != null && Objects.equals(cin, person.cin);
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }


    public void Reserver(Salle s, Mariage m) {
        m.setSalle(s);
        if (this.genre.equals(Genre.Homme)) {
            m.setEpoux(this);
            this.marriagesHomme.add(m);
        } else {
            m.setEpouse(this);
            this.marriagesFemme.add(m);
        }

    }

    public void Signer(Mariage m){
            if(this.genre==Genre.Homme) {
                m.setEpoux(this);
            }
            else {
                m.setEpouse(this);
            }
    }
}
