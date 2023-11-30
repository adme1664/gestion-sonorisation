package com.adme.gestion.sonorisation.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class ProclamateurDomain extends BaseEntityDomain {

  UUID proclamateurId;

  String nomFamille;

  String prenom;

  String sexe;

  LocalDate dateNaissance;

  String addresse;

  String telephone;

  String email;

  LocalDate dateBapteme;

  boolean ancien;

  boolean serviteurMinisteriel;

  boolean pionnierPermanent;

  boolean assigne;

  boolean servirDansVisio;

  boolean servirDansConsole;

  boolean servirDansMicrophone;

  boolean servirDansEstrade;

  boolean nePeutPlusServir;

  List<AssignationDomain> assignations;

}
