package com.adme.gestion.sonorisation.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record AssignationMicrophoneDomain(
    UUID assignationId,
    ProclamateurDomain proclamateur,
    ProgrammeSonorisationDomain programmeSonorisation,
    LocalDate dateAssignation,
    LocalDateTime dateCreation,

    String userCreate,

    LocalDateTime dateModification,

    String userModification

) {

}
