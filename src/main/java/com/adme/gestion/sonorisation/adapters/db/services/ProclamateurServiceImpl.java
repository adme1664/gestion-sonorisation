package com.adme.gestion.sonorisation.adapters.db.services;

import static com.adme.gestion.sonorisation.utils.Constants.PEUT_SERVIR;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.adapters.db.repository.ProclamateurRepository;
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


  public ProclamateurServiceImpl(ProclamateurRepository proclamateurRepository) {
    this.proclamateurRepository = proclamateurRepository;
  }


  @Override
  public Proclamateur saveOrUpdate(Proclamateur proclamateur) {
    log.info("Save new proclamateur in db");
    return this.proclamateurRepository.save(proclamateur);
  }

  @Override
  public Proclamateur getById(UUID proclamateurId) {
    log.info("Retrieve info for proclamateur {}", proclamateurId.toString());
    return  this.proclamateurRepository.findById(proclamateurId).orElse(null);
  }

  @Override
  public List<Proclamateur> getAll() {
    return this.proclamateurRepository.findAll();
  }

  @Override
  public List<Proclamateur> getListOfProclamateursNotYetAssign() {
    return null;
  }

  @Override
  public List<Proclamateur> getListOfAssignedProclamateurs() {
    return null;
  }

  @Override
  public List<Proclamateur> getAllProclamateurPeutServirDansMicrophone() {
    return this.proclamateurRepository.findProclamateurByServirDansMicrophone(PEUT_SERVIR);
  }

  @Override
  public List<Proclamateur> getAllProclamateurPeutServirDansVisio() {
    return this.proclamateurRepository.findProclamateurByServirDansVisio(PEUT_SERVIR);
  }

  @Override
  public List<Proclamateur> getAllProclamateurPeutServirDansConsole() {
    return this.proclamateurRepository.findProclamateurByServirDansConsole(PEUT_SERVIR);
  }

  @Override
  public List<Proclamateur> getAllProclamteurPeutServirDansEstrade() {
    return this.proclamateurRepository.findProclamateurByServirDansEstrade(PEUT_SERVIR);
  }
}
