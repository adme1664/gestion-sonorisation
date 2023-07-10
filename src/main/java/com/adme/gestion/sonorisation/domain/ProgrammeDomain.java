package com.adme.gestion.sonorisation.domain;

import com.adme.gestion.sonorisation.models.TypeProgramme;
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
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgrammeDomain {

  UUID programmeId;

  LocalDate dateCommencement;

  String nomProgramme;

  LocalDate dateFin;

  TypeProgramme typeProgramme;

  LocalDateTime dateCreation;

  String userCreate;

  LocalDateTime dateModification;

  String userModification;

}
