package com.adme.gestion.sonorisation.adapters.db.mapper;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProclamateurMapper {

  Proclamateur domainToEntity(ProclamateurDomain proclamateurDomain);

  @Mapping(target = "userModification", source = "userModification")
  @Mapping(target = "userCreate", source = "userCreate")
  @Mapping(target = "dateModification", source = "dateModification")
  @Mapping(target = "dateCreation", source = "dateCreation")
  @Mapping(target = "serviteurMinisteriel", source = "serviteurMinisteriel")
  @Mapping(target = "servirDansVisio", source = "servirDansVisio")
  @Mapping(target = "servirDansMicrophone", source = "servirDansMicrophone")
  @Mapping(target = "servirDansConsole", source = "servirDansConsole")
  @Mapping(target = "pionnierPermanent", source = "pionnierPermanent")
  @Mapping(target = "nePeutPlusServir", source = "nePeutPlusServir")
  @Mapping(target = "dateBapteme", source = "dateBapteme")
  @Mapping(target = "dateNaissance", source = "dateNaissance")
  @Mapping(target = "assigne", source = "assigne")
  @Mapping(target = "ancien", source = "ancien")
  @Mapping(target = "assignationConsoles", source = "assignationConsoles")
  @Mapping(target = "assignationMicrophones", source = "assignationMicrophones")
  @Mapping(target = "assignationVisioConferences", source = "assignationVisioConferences")
  ProclamateurDomain entityToDomain(Proclamateur proclamateur);

  List<ProclamateurDomain> mapToList(List<Proclamateur> proclamateurs);

  void updateProclamateur(ProclamateurDomain update, @MappingTarget Proclamateur saved);
}
