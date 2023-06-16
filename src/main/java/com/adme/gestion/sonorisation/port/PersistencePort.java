package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import java.util.List;
import java.util.UUID;

public interface PersistencePort {
  //todo change domain to enity
  ProclamateurDomain saveOrUpdateProclamateur(ProclamateurDomain proclamateurDomain);
  ProclamateurDomain getProclamateurById(UUID proclamateurId);
  List<ProclamateurDomain> getAllProclamateurs();
}
