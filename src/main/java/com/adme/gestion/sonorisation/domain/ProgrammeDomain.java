package com.adme.gestion.sonorisation.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ProgrammeDomain(
    UUID programmeId,
    LocalDate dateCommencement,

    String nomProgramme,
    LocalDate dateFin,
    Set<AssignationConsoleDomain> assignationConsoles,
    Set<AssignationVisioConferenceDomain> assignationVisioConferences,

    Set<AssignationMicrophoneDomain> assignationMicrophones,
    LocalDateTime dateCreation,

    String userCreate,

    LocalDateTime dateModification,

    String userModification
) {

}
