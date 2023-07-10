package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.adapters.db.mapper.ProclamateurMapper;
import com.adme.gestion.sonorisation.adapters.db.repository.ProclamateurRepository;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProclamateurPersistenceGateway  implements ProclamateurPersistencePort {

  ProclamateurRepository proclamateurRepository;
  ProclamateurMapper proclamateurMapper;

  public ProclamateurPersistenceGateway(
      ProclamateurRepository proclamateurRepository,
      ProclamateurMapper proclamateurMapper) {
    this.proclamateurRepository = proclamateurRepository;
    this.proclamateurMapper = proclamateurMapper;
  }


  @Override
  public ProclamateurDomain saveOrUpdate(Object obj) {

    Proclamateur proclamateur = this.proclamateurMapper.domainToEntity((ProclamateurDomain) obj);

    return this.proclamateurMapper.entityToDomain(this.proclamateurRepository.save(proclamateur));
  }

  @Override
  public ProclamateurDomain getProclamateurById(UUID proclamateurId) {
    return this.proclamateurMapper.entityToDomain(
        this.proclamateurRepository.findById(proclamateurId).orElse(null));
  }

  @Override
  public List<ProclamateurDomain> getAllProclamateurs() {
    return this.proclamateurRepository.findAll().stream().map(proclamateurMapper::entityToDomain)
        .toList();
  }

  @Override
  public List<ProclamateurDomain> getListOfProclamateursNotYetAssign() {
   return null;
  }

  @Override
  public List<ProclamateurDomain> getListOfAssignedProclamateurs() {
    return null;
  }

  /**
   * Peut servir dans le service de passeurs de Microphones
   * @param peutServir {@linkplain Boolean} Oui ou Non
   * @return {@linkplain List} of {@linkplain ProclamateurDomain}
   */
  @Override
  public List<ProclamateurDomain> getAllProclamateurPeutServirDansMicrophone(boolean peutServir) {
    return this.proclamateurRepository.findProclamateurByServirDansMicrophone(peutServir)
        .stream().map(proclamateurMapper::entityToDomain)
        .toList();
  }

  /**
   * Peut servir dans le service de passeurs de Visio
   * @param peutServir {@linkplain Boolean} Oui ou Non
   * @return {@linkplain List} of {@linkplain ProclamateurDomain}
   */
  @Override
  public List<ProclamateurDomain> getAllProclamateurPeutServirDansVisio(boolean peutServir) {
    return this.proclamateurRepository.findProclamateurByServirDansVisio(peutServir)
        .stream().map(proclamateurMapper::entityToDomain)
        .toList();
  }

  /**
   * Peut servir dans le service de passeurs de Console
   * @param peutServir {@linkplain Boolean} Oui ou Non
   * @return {@linkplain List} of {@linkplain ProclamateurDomain}
   */
  @Override
  public List<ProclamateurDomain> getAllProclamateurPeutServirDansConsole(boolean peutServir) {
    return this.proclamateurRepository.findProclamateurByServirDansConsole(peutServir)
        .stream().map(proclamateurMapper::entityToDomain)
        .toList();
  }

  /**
   * Peut servir dans le service de passeurs dans Estrade
   * @param peutServir {@linkplain Boolean} Oui ou Non
   * @return {@linkplain List} of {@linkplain ProclamateurDomain}
   */

  @Override
  public List<ProclamateurDomain> getAllProclamateurPeutServirDansEstrade(boolean peutServir) {
    return this.proclamateurRepository.findProclamateurByServirDansEstrade(peutServir)
        .stream().map(proclamateurMapper::entityToDomain)
        .toList();
  }
}
