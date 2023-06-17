package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.adapters.db.mapper.ProclamateurMapper;
import com.adme.gestion.sonorisation.adapters.db.mapper.ProgrammeMapper;
import com.adme.gestion.sonorisation.adapters.db.services.ProclamateurService;
import com.adme.gestion.sonorisation.adapters.db.services.ProgrammeService;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersistenceGatewayAdapter implements PersistencePort{
  ProclamateurService proclamateurService;
  ProclamateurMapper proclamateurMapper;

  ProgrammeService programmeService;

  ProgrammeMapper programmeMapper;

  public PersistenceGatewayAdapter(ProclamateurService proclamateurService,
      ProclamateurMapper proclamateurMapper, ProgrammeService programmeService,
      ProgrammeMapper programmeMapper) {
    this.proclamateurService = proclamateurService;
    this.proclamateurMapper = proclamateurMapper;
    this.programmeService = programmeService;
    this.programmeMapper = programmeMapper;
  }


  @Override
  public ProgrammeDomain saveOrUpdateProgramme(ProgrammeDomain programmeDomain) {
    Programme  programme = this.programmeMapper.domainToEntity(programmeDomain);
    return this.programmeMapper.entityToDomain(this.programmeService.saveOrUpdateProgramme(programme));
  }

  @Override
  public ProgrammeDomain getProgrammeById(UUID programmeId) {
    return this.programmeMapper.entityToDomain(this.programmeService.getProgrammeById(programmeId));
  }

  @Override
  public List<ProgrammeDomain> getAllProgrammes() {
    return this.programmeMapper.mapToList(this.programmeService.getAllProgrammes());
  }

  @Override
  public ProclamateurDomain saveOrUpdateProclamateur(ProclamateurDomain proclamateurDomain) {
    Proclamateur proclamateur = this.proclamateurMapper.domainToEntity(proclamateurDomain);
    return this.proclamateurMapper.entityToDomain(proclamateurService.saveOrUpdate(proclamateur));
  }

  @Override
  public ProclamateurDomain getProclamateurById(UUID proclamateurId) {
    return this.proclamateurMapper.entityToDomain(proclamateurService.getById(proclamateurId));
  }

  @Override
  public List<ProclamateurDomain> getAllProclamateurs() {
    return this.proclamateurMapper.mapToList(proclamateurService.getAll());
  }
}
