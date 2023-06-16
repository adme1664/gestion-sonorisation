package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import java.util.List;
import java.util.UUID;

public interface ProclamateurService {
  //todo change domain to entity
  ProclamateurDomain saveOrUpdate(ProclamateurDomain proclamateurDomain);
  ProclamateurDomain getById(UUID proclamateurId);
  List<ProclamateurDomain> getAll();



}
