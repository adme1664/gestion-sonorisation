package com.adme.gestion.sonorisation.adapters.db.repository;

import com.adme.gestion.sonorisation.adapters.db.entities.Assignation;
import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignationRepository extends JpaRepository<Assignation, UUID> {

  List<Assignation> findAssignationByProgramme(Programme programme);

  @Query(value = "select a.assignation_id, a.date_assignation "
      + "from /gestion_sonorisation/.assignation "
      + "where a.date_assignation = "
      + "(select max(b.date_assignation) "
      + "from /gestion_sonorisation/.assignation b where b.proclamateur_id = :proclamateurId) "
      + "and a.programme_id = :programmeId", nativeQuery = true)
  Optional<Assignation> findLastAssignationByProgrammeAndProclamateur(UUID programmeId,
      UUID proclamateurId);

  List<Assignation> findByDateAssignation(LocalDate dateAssignation);

  List<Assignation> findByTypeAssignationAndDateAssignation(String typeAssignation,
      LocalDate dateAssignation);

  Optional<Assignation> findByDateAssignationAndProclamateur(LocalDate dateAssignation,
      Proclamateur proclamateur);


}
