package com.adme.gestion.sonorisation.domain;

import com.adme.gestion.sonorisation.models.TypeAssignation;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignationDomain {

  UUID assignationId;

  ProclamateurDomain proclamateur;

  ProgrammeDomain programme;

  TypeAssignation typeAssignation;

  LocalDate dateAssignation;

  LocalDateTime dateCreation;

  String userCreate;

  LocalDateTime dateModification;

  String userModification;

}
