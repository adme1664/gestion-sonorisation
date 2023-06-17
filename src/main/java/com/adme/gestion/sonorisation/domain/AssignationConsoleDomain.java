package com.adme.gestion.sonorisation.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record AssignationConsoleDomain(UUID assignationId,
                                       ProclamateurDomain proclamateur,
                                       ProgrammeDomain programmeSonorisation,
                                       LocalDateTime dateAssignation,
                                       LocalDateTime dateCreation,

                                       String userCreate,

                                       LocalDateTime dateModification,

                                       String userModification)  {

}
