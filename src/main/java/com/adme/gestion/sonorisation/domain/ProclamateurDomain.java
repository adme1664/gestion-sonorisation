package com.adme.gestion.sonorisation.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ProclamateurDomain {

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

  LocalDateTime dateCreation;

  String userCreate;

  LocalDateTime dateModification;

  String userModification;

  List<AssignationDomain> assignations;

}
