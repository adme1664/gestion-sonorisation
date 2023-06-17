package com.adme.gestion.sonorisation.adapters.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @Column(name = "date_creation")
  @CreatedDate
  LocalDateTime dateCreation;

  @Column(name = "user_create")
  @CreatedBy
  String userCreate;

  @Column(name = "date_modification")
  @LastModifiedDate
  LocalDateTime dateModification;

  @Column(name = "user_modification")
  @LastModifiedBy
  String userModification;
}
