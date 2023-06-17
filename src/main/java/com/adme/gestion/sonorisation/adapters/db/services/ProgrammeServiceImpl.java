package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.adapters.db.repository.ProgrammeRepository;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgrammeServiceImpl implements ProgrammeService{
  ProgrammeRepository programmeRepository;

  public ProgrammeServiceImpl(ProgrammeRepository programmeRepository) {
    this.programmeRepository = programmeRepository;
  }

  @Override
  public Programme saveOrUpdateProgramme(Programme programme) {
    return this.programmeRepository.save(programme);
  }

  @Override
  public Programme getProgrammeById(UUID programmeId) {
    return this.programmeRepository.findById(programmeId).orElse(null);
  }

  @Override
  public List<Programme> getAllProgrammes() {
    return this.programmeRepository.findAll();
  }
}
