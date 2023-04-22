package emi.ma.gestionfete.repositories;

import emi.ma.gestionfete.models.Mariage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MariageRepository extends JpaRepository<Mariage,Long> {
}
