package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import java.util.UUID;

public interface AssignationPersistencePort extends PersistencePort {

  AssignationDomain getAssignation(UUID assignationId);

  List<AssignationDomain> findAssignationsByProgramme(ProgrammeDomain programme);
}
