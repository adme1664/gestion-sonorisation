package com.adme.gestion.sonorisation.adapters.db.repository;

import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeRepository extends
    JpaRepository<Programme, UUID> {

  @Query(value = "select a.programme_id, a.type_programme, max(a.date_fin) as date_fin, "
      + "a.nom_programme, a.date_commencement, a.user_create, a.date_creation, "
      + "a.date_modification, a.user_modification from /gestion_sonorisation/.programme a "
      + "where a.type_programme= :typeProgramme",
  nativeQuery = true)
  Optional<Programme> findLastProgrammeByType(String typeProgramme);

}
