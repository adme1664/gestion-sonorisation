package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.adapters.db.mapper.ProclamateurMapper;
import com.adme.gestion.sonorisation.adapters.db.repository.ProclamateurRepository;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProclamateurServiceImpl implements ProclamateurService {

  ProclamateurRepository proclamateurRepository;

  ProclamateurMapper proclamateurMapper;

  public ProclamateurServiceImpl(ProclamateurRepository proclamateurRepository,
      ProclamateurMapper proclamateurMapper) {
    this.proclamateurRepository = proclamateurRepository;
    this.proclamateurMapper = proclamateurMapper;
  }


  @Override
  public ProclamateurDomain saveOrUpdate(ProclamateurDomain proclamateurDomain) {
    Proclamateur proclamateur = this.proclamateurMapper.domainToEntity(proclamateurDomain);
    log.info("Save new proclamateur in db");
    return this.proclamateurMapper.entityToDomain(this.proclamateurRepository.save(proclamateur));
  }

  @Override
  public ProclamateurDomain getById(UUID proclamateurId) {
    log.info("Retrieve info for proclamateur {}", proclamateurId.toString());
    return this.proclamateurMapper.entityToDomain(
        this.proclamateurRepository.findById(proclamateurId).orElse(null));
  }

  @Override
  public List<ProclamateurDomain> getAll() {
    return this.proclamateurMapper.mapToList(this.proclamateurRepository.findAll());
  }
}
