package com.adme.gestion.sonorisation.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ProgrammeSonorisationDomain(
    UUID programmeId,
    LocalDate dateCommencement,
    LocalDate dateFin,
    Set<AssignationConsoleDomain> assignationsConsoles,
    Set<AssignationVisioConferenceDomain> assignationVisioConferences,
    LocalDateTime dateCreation,

    String userCreate,

    LocalDateTime dateModification,

    String userModification
) {

}
