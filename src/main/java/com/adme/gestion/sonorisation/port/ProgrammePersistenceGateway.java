package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.adapters.db.mapper.ProgrammeMapper;
import com.adme.gestion.sonorisation.adapters.db.repository.ProgrammeRepository;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProgrammePersistenceGateway implements ProgrammePersistencePort {

  ProgrammeRepository programmeRepository;
  ProgrammeMapper programmeMapper;

  public ProgrammePersistenceGateway(
      ProgrammeRepository programmeRepository,
      ProgrammeMapper programmeMapper) {
    this.programmeRepository = programmeRepository;
    this.programmeMapper = programmeMapper;
  }


  @Override
  public ProgrammeDomain saveOrUpdate(Object obj) {
    ProgrammeDomain programmeDomain = (ProgrammeDomain) obj;

    return this.programmeMapper.entityToDomain(
        this.programmeRepository
            .save(this.programmeMapper.domainToEntity(programmeDomain)));
  }

  @Override
  public ProgrammeDomain getProgrammeById(UUID programmeId) {
    return this.programmeMapper
        .entityToDomain(this.programmeRepository.findById(programmeId).orElse(null));
  }

  @Override
  public ProgrammeDomain getLastProgrammeByType(String typeProgramme) {
    return this.programmeMapper.entityToDomain(
        this.programmeRepository.findLastProgrammeByType(typeProgramme).orElse(null));
  }

  @Override
  public List<ProgrammeDomain> getAllProgrammes() {
    return this.programmeRepository
        .findAll()
        .stream()
        .map(programmeMapper::entityToDomain)
        .toList();
  }
}
