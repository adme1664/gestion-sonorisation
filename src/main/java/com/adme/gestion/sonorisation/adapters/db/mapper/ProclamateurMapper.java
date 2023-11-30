package com.adme.gestion.sonorisation.adapters.db.mapper;

import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = IgnoreUnmappedConfig.class)
public interface ProclamateurMapper {

  @Mapping(target = "nomFamille", source = "nomFamille")
  @Mapping(target = "prenom", source = "prenom")
  @Mapping(target = "sexe", source = "sexe")
  @Mapping(target = "dateNaissance", source = "dateNaissance")
  @Mapping(target = "addresse", source = "addresse")
  @Mapping(target = "telephone", source = "telephone")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "dateBapteme", source = "dateBapteme")
  @Mapping(target = "serviteurMinisteriel", source = "serviteurMinisteriel")
  @Mapping(target = "servirDansVisio", source = "servirDansVisio")
  @Mapping(target = "servirDansMicrophone", source = "servirDansMicrophone")
  @Mapping(target = "servirDansConsole", source = "servirDansConsole")
  @Mapping(target = "pionnierPermanent", source = "pionnierPermanent")
  @Mapping(target = "nePeutPlusServir", source = "nePeutPlusServir")
  @Mapping(target = "assigne", source = "assigne")
  @Mapping(target = "ancien", source = "ancien")
  @Mapping(target = "assignations", ignore = true)
  Proclamateur domainToEntity(ProclamateurDomain proclamateurDomain);

  @Mapping(target = "proclamateurId", source = "proclamateurId")
  @Mapping(target = "nomFamille", source = "nomFamille")
  @Mapping(target = "prenom", source = "prenom")
  @Mapping(target = "sexe", source = "sexe")
  @Mapping(target = "dateNaissance", source = "dateNaissance")
  @Mapping(target = "addresse", source = "addresse")
  @Mapping(target = "telephone", source = "telephone")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "dateBapteme", source = "dateBapteme")
  @Mapping(target = "serviteurMinisteriel", source = "serviteurMinisteriel")
  @Mapping(target = "servirDansVisio", source = "servirDansVisio")
  @Mapping(target = "servirDansMicrophone", source = "servirDansMicrophone")
  @Mapping(target = "servirDansConsole", source = "servirDansConsole")
  @Mapping(target = "pionnierPermanent", source = "pionnierPermanent")
  @Mapping(target = "nePeutPlusServir", source = "nePeutPlusServir")
  @Mapping(target = "assigne", source = "assigne")
  @Mapping(target = "ancien", source = "ancien")
  @Mapping(target = "assignations", ignore = true)
  @BaseEntityMapper
  ProclamateurDomain entityToDomain(Proclamateur proclamateur);


}
