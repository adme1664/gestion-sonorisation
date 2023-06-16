package com.adme.gestion.sonorisation.services;

import com.adme.gestion.sonorisation.adapters.db.entities.AssignationMicrophone;
import com.adme.gestion.sonorisation.adapters.db.entities.ProgrammeSonorisation;
import com.adme.gestion.sonorisation.adapters.db.entities.AssignationConsole;
import com.adme.gestion.sonorisation.adapters.db.entities.AssignationVisioConference;
import com.adme.gestion.sonorisation.exceptions.NotFoundException;
import com.adme.gestion.sonorisation.adapters.db.repository.ProgrammeSonorisationRepository;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProgrammeSonorisationService {

  ProgrammeSonorisationRepository programmeSonorisationRepository;

  public ProgrammeSonorisationService(
      ProgrammeSonorisationRepository programmeSonorisationRepository) {
    this.programmeSonorisationRepository = programmeSonorisationRepository;
  }

  public void setAssignationConsole(ProgrammeSonorisation programme,
      Set<AssignationConsole> assignationConsoles) {
    if (programme.getAssignationConsoles().isEmpty()) {
      programme.setAssignationConsoles(assignationConsoles);
    }
  }

  public void setAssignationVisioConference(ProgrammeSonorisation programme,
      Set<AssignationVisioConference> assignationVisioConferences) {
    if (programme.getAssignationVisioConferences().isEmpty()) {
      programme.setAssignationVisioConferences(assignationVisioConferences);
    }
  }

  public void setAssignationMicrophones(ProgrammeSonorisation programme,
      Set<AssignationMicrophone> assignationMicrophones) {
    if (programme.getAssignationVisioConferences().isEmpty()) {
      programme.setAssignationMicrophones(assignationMicrophones);
    }
  }

  public ProgrammeSonorisation save(ProgrammeSonorisation programmeSonorisation) {
    if (Objects.nonNull(programmeSonorisation.getProgrammeId())) {

      log.info("Mise a jour du programme id {} pour la date du {} au {}",
          programmeSonorisation.getProgrammeId(),
          programmeSonorisation.getDateCommencement(),
          programmeSonorisation.getDateFin());

      return prepareForUpdate(programmeSonorisation);

    } else {

      log.info("Enregistrement d'un nouveau programme pour la date du {} au {}",
          programmeSonorisation.getDateCommencement(),
          programmeSonorisation.getDateFin());

      return this.programmeSonorisationRepository.save(programmeSonorisation);
    }

  }

  public ProgrammeSonorisation prepareForUpdate(ProgrammeSonorisation programme) {
    ProgrammeSonorisation programmeDb = getProgrammeSonorisation(programme.getProgrammeId());
    validateProgramme(programme, programmeDb);
    return programmeSonorisationRepository.save(programmeDb);
  }

  public void validateProgramme(ProgrammeSonorisation programme,
      ProgrammeSonorisation programmeDb) {
    log.info("Validation d'un programme existant...");
    programmeDb.setNomProgramme(programme.getNomProgramme());
    programmeDb.setDateCommencement(programme.getDateCommencement());
    programmeDb.setDateFin(programme.getDateFin());
    programmeDb.setAssignationConsoles(programme.getAssignationConsoles());
    programmeDb.setAssignationMicrophones(programme.getAssignationMicrophones());
    programmeDb.setAssignationVisioConferences(programme.getAssignationVisioConferences());
  }

  public ProgrammeSonorisation getProgrammeSonorisation(UUID programmeId) {
    return this.programmeSonorisationRepository
        .findById(programmeId).orElseThrow(NotFoundException::new);
  }


}
