package com.adme.gestion.sonorisation.domain;

import com.adme.gestion.sonorisation.models.TypeProgramme;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgrammeDomain extends BaseEntityDomain {

  UUID programmeId;

  LocalDate dateCommencement;

  String nomProgramme;

  LocalDate dateFin;

  TypeProgramme typeProgramme;

}
