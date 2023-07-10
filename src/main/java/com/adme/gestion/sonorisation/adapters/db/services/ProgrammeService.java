package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import java.util.List;
import java.util.UUID;

public interface ProgrammeService {
  Programme saveOrUpdateProgramme(Programme programme);

  Programme getProgrammeById(UUID programmeId);

  Programme getLastProgrammeByType(String typeProgramme);

  List<Programme> getAllProgrammes();
}
