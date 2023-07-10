package com.adme.gestion.sonorisation.adapters.db.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
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

@Table(name = "programme", schema = "gestion_sonorisation")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Programme extends BaseEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "programme_id")
  UUID programmeId;

  @Column(name = "nom_programme", nullable = false)
  String nomProgramme;

  @Column(name = "type_programme", nullable = false)
  String typeProgramme;

  @Column(name = "date_commencement", nullable = false)
  LocalDate dateCommencement;

  @Column(name = "date_fin", nullable = false)
  LocalDate dateFin;

  @OneToMany(mappedBy = "programme", cascade = CascadeType.ALL)
  List<Assignation> assignations;

}
