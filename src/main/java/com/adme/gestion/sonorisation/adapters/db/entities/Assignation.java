package com.adme.gestion.sonorisation.adapters.db.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "assignation", schema = "gestion_sonorisation")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class Assignation extends BaseEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "assignation_id")
  UUID assignationId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="proclamateur_id",referencedColumnName = "proclamateur_id", nullable = false)
  private Proclamateur proclamateur;

  @Column(name = "type_assignation", nullable = false)
  String typeAssignation;

  @ManyToOne
  @JoinColumn(name = "programme_id", referencedColumnName = "programme_id", nullable = false)
  Programme programme;

  @Column(name = "date_assignation", nullable = false)
  LocalDate dateAssignation;

}
