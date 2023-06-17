package com.adme.gestion.sonorisation.services;

import com.adme.gestion.sonorisation.adapters.db.entities.AssignationMicrophone;
import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.adapters.db.entities.AssignationConsole;
import com.adme.gestion.sonorisation.adapters.db.entities.AssignationVisioConference;
import com.adme.gestion.sonorisation.exceptions.NotFoundException;
import com.adme.gestion.sonorisation.adapters.db.repository.ProgrammeRepository;
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

  ProgrammeRepository programmeRepository;

  public ProgrammeSonorisationService(
      ProgrammeRepository programmeRepository) {
    this.programmeRepository = programmeRepository;
  }

  public void setAssignationConsole(Programme programme,
      Set<AssignationConsole> assignationConsoles) {
    if (programme.getAssignationConsoles().isEmpty()) {
      programme.setAssignationConsoles(assignationConsoles);
    }
  }

  public void setAssignationVisioConference(Programme programme,
      Set<AssignationVisioConference> assignationVisioConferences) {
    if (programme.getAssignationVisioConferences().isEmpty()) {
      programme.setAssignationVisioConferences(assignationVisioConferences);
    }
  }

  public void setAssignationMicrophones(Programme programme,
      Set<AssignationMicrophone> assignationMicrophones) {
    if (programme.getAssignationVisioConferences().isEmpty()) {
      programme.setAssignationMicrophones(assignationMicrophones);
    }
  }

  public Programme save(Programme programme) {
    if (Objects.nonNull(programme.getProgrammeId())) {

      log.info("Mise a jour du programme id {} pour la date du {} au {}",
          programme.getProgrammeId(),
          programme.getDateCommencement(),
          programme.getDateFin());

      return prepareForUpdate(programme);

    } else {

      log.info("Enregistrement d'un nouveau programme pour la date du {} au {}",
          programme.getDateCommencement(),
          programme.getDateFin());

      return this.programmeRepository.save(programme);
    }

  }

  public Programme prepareForUpdate(Programme programme) {
    Programme programmeDb = getProgrammeSonorisation(programme.getProgrammeId());
    validateProgramme(programme, programmeDb);
    return programmeRepository.save(programmeDb);
  }

  public void validateProgramme(Programme programme,
      Programme programmeDb) {
    log.info("Validation d'un programme existant...");
    programmeDb.setNomProgramme(programme.getNomProgramme());
    programmeDb.setDateCommencement(programme.getDateCommencement());
    programmeDb.setDateFin(programme.getDateFin());
    programmeDb.setAssignationConsoles(programme.getAssignationConsoles());
    programmeDb.setAssignationMicrophones(programme.getAssignationMicrophones());
    programmeDb.setAssignationVisioConferences(programme.getAssignationVisioConferences());
  }

  public Programme getProgrammeSonorisation(UUID programmeId) {
    return this.programmeRepository
        .findById(programmeId).orElseThrow(NotFoundException::new);
  }


}
