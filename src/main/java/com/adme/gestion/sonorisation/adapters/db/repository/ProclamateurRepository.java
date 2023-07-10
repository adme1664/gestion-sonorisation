package com.adme.gestion.sonorisation.adapters.db.repository;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProclamateurRepository extends JpaRepository<Proclamateur, UUID> {
  //List<Proclamateur> rechercheProclamteursNonAssigne(LocalDate dateCommencenment, LocalDate dateFin);
  List<Proclamateur> findProclamateurByServirDansMicrophone(boolean servir);

  List<Proclamateur> findProclamateurByServirDansConsole(boolean servir);

  List<Proclamateur> findProclamateurByServirDansVisio(boolean servir);

  List<Proclamateur> findProclamateurByServirDansEstrade(boolean servir);

}
