package com.adme.gestion.sonorisation.port;

import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import java.util.List;
import java.util.UUID;

public interface ProclamateurPersistencePort extends PersistencePort {
  ProclamateurDomain getProclamateurById(UUID proclamateurId);
  List<ProclamateurDomain> getAllProclamateurs();
  List<ProclamateurDomain> getListOfProclamateursNotYetAssign();
  List<ProclamateurDomain> getListOfAssignedProclamateurs();
  List<ProclamateurDomain> getAllProclamateurPeutServirDansMicrophone(boolean peutServir);
  List<ProclamateurDomain> getAllProclamateurPeutServirDansVisio(boolean peutServir);
  List<ProclamateurDomain> getAllProclamateurPeutServirDansConsole(boolean peutServir);
  List<ProclamateurDomain> getAllProclamateurPeutServirDansEstrade(boolean peutServir);

}
