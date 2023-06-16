package com.adme.gestion.sonorisation.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ProclamateurDomain (
    UUID proclamateurId,
    String nomFamille,
    String prenom,
    String sexe,
    LocalDate dateNaissance,
    String addresse,
    String telephone,
    String email,
    boolean ancien,
    boolean serviteurMinisteriel,
    boolean pionnierPermanent,
    boolean assigne,
    boolean servirDansVisio,
    boolean servirDansConsole,
    boolean servirDansMicrophone,
    boolean nePeutPlusServir,
    Set<AssignationConsoleDomain> assignationConsoles,
    Set<AssignationMicrophoneDomain> assignationMicrophones,
    Set<AssignationVisioConferenceDomain> assignationVisioConferences,
    LocalDateTime dateCreation,

    String userCreate,

    LocalDateTime dateModification,

    String userModification) {

}
