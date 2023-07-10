package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import java.util.List;
import java.util.UUID;

public interface ProclamateurService {
  Proclamateur saveOrUpdate(Proclamateur proclamateur);
  Proclamateur getById(UUID proclamateurId);
  List<Proclamateur> getAll();
  List<Proclamateur> getListOfProclamateursNotYetAssign();
  List<Proclamateur> getListOfAssignedProclamateurs();
  List<Proclamateur> getAllProclamateurPeutServirDansMicrophone();
  List<Proclamateur> getAllProclamateurPeutServirDansVisio();
  List<Proclamateur> getAllProclamateurPeutServirDansConsole();
  List<Proclamateur> getAllProclamteurPeutServirDansEstrade();
}
