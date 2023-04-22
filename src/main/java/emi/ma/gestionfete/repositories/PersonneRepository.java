package emi.ma.gestionfete.repositories;

import emi.ma.gestionfete.models.Personne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneRepository extends JpaRepository<Personne,String> {
    Page<Personne> findByNomContains(String keyword, PageRequest of);
    List<Personne> findByNom(String name);
}
