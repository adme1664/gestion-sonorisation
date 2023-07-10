package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import java.util.UUID;

public interface ProgrammePersistencePort extends PersistencePort{

  ProgrammeDomain getProgrammeById(UUID programmeId);

  ProgrammeDomain getLastProgrammeByType(String typeProgramme);

  List<ProgrammeDomain> getAllProgrammes();

}
