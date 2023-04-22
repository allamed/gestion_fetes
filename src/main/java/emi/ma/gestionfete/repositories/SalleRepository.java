package emi.ma.gestionfete.repositories;

import emi.ma.gestionfete.models.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle,Long> {
}
