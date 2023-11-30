package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Assignation;
import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.adapters.db.mapper.AssignationMapper;
import com.adme.gestion.sonorisation.adapters.db.mapper.ProgrammeMapper;
import com.adme.gestion.sonorisation.adapters.db.repository.AssignationRepository;
import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import com.adme.gestion.sonorisation.port.AssignationPort;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AssignationAdapter implements AssignationPort {

  AssignationMapper assignationMapper;
  ProgrammeMapper programmeMapper;
  AssignationRepository assignationRepository;

  public AssignationAdapter(
      AssignationMapper assignationMapper,
      ProgrammeMapper programmeMapper,
      AssignationRepository assignationRepository) {
    this.assignationMapper = assignationMapper;
    this.programmeMapper = programmeMapper;
    this.assignationRepository = assignationRepository;
  }


  @Override
  public AssignationDomain findById(UUID assignationId) {
    return this.assignationMapper.entityToFullDomain(
        assignationRepository.getReferenceById(assignationId)
    );
  }

  @Override
  public List<AssignationDomain> findAssignationParProgramme(ProgrammeDomain programmeDomain) {
    return this.assignationRepository.findAssignationByProgramme(
            this.programmeMapper.domainToEntity(programmeDomain))
        .stream()
        .map(this.assignationMapper::entityToFullDomain)
        .toList();
  }

  @Override
  public List<AssignationDomain> findByDateAssignation(LocalDate dateAssignation) {
    return null;
  }

  @Override
  public boolean isProclamateurAlreadyAssigned(ProclamateurDomain proclamateurDomain,
      LocalDate dateAssignation) {

    Proclamateur proclamateur = Proclamateur
        .builder()
        .proclamateurId(proclamateurDomain.getProclamateurId())
        .build();

    return this.assignationRepository
        .findByDateAssignationAndProclamateur(dateAssignation, proclamateur)
        .isPresent();

  }

  @Override
  public AssignationDomain saveOrUpdate(AssignationDomain assignationDomain) {

    ProclamateurDomain proclamateurDomain = assignationDomain.getProclamateur();

    Assignation assignationEntity = this.assignationMapper.domainToEntity(assignationDomain);

    assignationEntity.setProclamateur(Proclamateur.builder()
        .proclamateurId(assignationDomain.getProclamateur().getProclamateurId()).build());

    assignationEntity.setProgramme(
        Programme.builder().programmeId(assignationDomain.getProgramme().getProgrammeId()).build());

    if (!isProclamateurAlreadyAssigned(proclamateurDomain,
        assignationDomain.getDateAssignation())) {
      return this.assignationMapper
          .entityToFullDomain(this.assignationRepository.save(assignationEntity));
    } else {

      log.warn("Le proclamateur {} est deja assignation pour la date du {}",
          proclamateurDomain.getNomFamille() + " " + proclamateurDomain.getPrenom(),
          assignationDomain.getDateAssignation());
    }

    return null;
  }

  @Override
  public Optional<AssignationDomain> findLastAssignationByProgrammeAndProclamateur(
      UUID programmeId,
      UUID proclamateurId) {

    return this.assignationRepository
        .findLastAssignationByProgrammeAndProclamateur(programmeId, proclamateurId)
        .map(assignationMapper::entityToDomain);
  }

}
