package emi.ma.gestionfete.repositories;

import emi.ma.gestionfete.models.Invite;
import emi.ma.gestionfete.models.StatusInvite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteRepository extends JpaRepository<Invite,String> {
    public List<Invite> findByStatut(StatusInvite statut);

    Page<Invite> findByNomContains(String keyword, PageRequest of);

    Invite findByCin(String cin);

    void deleteByCin(String cin);
}