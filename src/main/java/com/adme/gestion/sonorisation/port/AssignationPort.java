package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssignationPort {
  AssignationDomain saveOrUpdate(AssignationDomain assignationDomain);

  AssignationDomain findById(UUID assignationId);

  List<AssignationDomain> findAssignationParProgramme(ProgrammeDomain programmeDomain);

  List<AssignationDomain> findByDateAssignation(LocalDate dateAssignation);

  boolean isProclamateurAlreadyAssigned(ProclamateurDomain proclamateurDomain, LocalDate dateAssignation);

  Optional<AssignationDomain> findLastAssignationByProgrammeAndProclamateur(UUID programmeId, UUID proclamateurId);

}
