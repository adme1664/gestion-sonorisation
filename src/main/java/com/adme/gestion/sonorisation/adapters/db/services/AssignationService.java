package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Assignation;
import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import java.util.List;
import java.util.UUID;

public interface AssignationService {
  Assignation saveOrUpdate(Assignation assignation);

  Assignation findById(UUID assignationId);

  List<Assignation> findAssignationParProgramme(Programme programme);

}
