package com.adme.gestion.sonorisation.domain;

import com.adme.gestion.sonorisation.models.TypeAssignation;
import java.time.LocalDate;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignationDomain extends BaseEntityDomain{
    UUID assignationId;

    ProclamateurDomain proclamateur;

    ProgrammeDomain programme;

    TypeAssignation typeAssignation;

    LocalDate dateAssignation;

}
