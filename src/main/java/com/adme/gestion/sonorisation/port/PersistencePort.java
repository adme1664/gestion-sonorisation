package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import java.util.UUID;

public interface PersistencePort {

  ProgrammeDomain saveOrUpdateProgramme(ProgrammeDomain programmeDomain);
  ProgrammeDomain getProgrammeById(UUID programmeId);
  List<ProgrammeDomain> getAllProgrammes();
  ProclamateurDomain saveOrUpdateProclamateur(ProclamateurDomain proclamateurDomain);
  ProclamateurDomain getProclamateurById(UUID proclamateurId);
  List<ProclamateurDomain> getAllProclamateurs();
}
