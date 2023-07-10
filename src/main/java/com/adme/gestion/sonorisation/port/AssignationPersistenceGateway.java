package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.adapters.db.mapper.AssignationMapper;
import com.adme.gestion.sonorisation.adapters.db.mapper.ProgrammeMapper;
import com.adme.gestion.sonorisation.adapters.db.repository.AssignationRepository;
import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AssignationPersistenceGateway implements AssignationPersistencePort {

  AssignationRepository assignationRepository;
  AssignationMapper assignationMapper;
  ProgrammeMapper programmeMapper;

  public AssignationPersistenceGateway(
      AssignationRepository assignationRepository,
      AssignationMapper assignationMapper,
      ProgrammeMapper programmeMapper) {
    this.assignationRepository = assignationRepository;
    this.assignationMapper = assignationMapper;
    this.programmeMapper = programmeMapper;
  }


  @Override
  public AssignationDomain getAssignation(UUID assignationId) {
    return this.assignationMapper
        .entityToDomain(this.assignationRepository.findById(assignationId).orElse(null));
  }

  @Override
  public List<AssignationDomain> findAssignationsByProgramme(ProgrammeDomain programme) {
    return this.assignationRepository.findAssignationByProgramme(
            this.programmeMapper.domainToEntity(programme))
        .stream()
        .map(this.assignationMapper::entityToDomain)
        .toList();
  }

  @Override
  public AssignationDomain saveOrUpdate(Object obj) {
    AssignationDomain assignationDomain = (AssignationDomain) obj;

    return this.assignationMapper.entityToDomain(
        this.assignationRepository
            .save(this.assignationMapper.domainToEntity(assignationDomain)));
  }
}
