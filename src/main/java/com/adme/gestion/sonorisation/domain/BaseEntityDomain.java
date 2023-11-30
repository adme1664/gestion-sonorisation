package com.adme.gestion.sonorisation.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityDomain {

  LocalDateTime dateCreate;

  String userCreate;

  LocalDateTime dateUpdate;

  String userUpdate;
}
