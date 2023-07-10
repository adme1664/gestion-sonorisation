package com.adme.gestion.sonorisation.adapters.db.repository;

import com.adme.gestion.sonorisation.adapters.db.entities.Assignation;
import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignationRepository extends JpaRepository<Assignation, UUID> {
      List<Assignation> findAssignationByProgramme(Programme programme);

}
