package com.adme.gestion.sonorisation.adapters.db.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "proclamateur", schema = "gestion_sonorisation")
@Entity
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Proclamateur extends BaseEntity {

  @Id
  @Column(name = "proclamateur_id")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  UUID proclamateurId;

  @Column(name = "nom_famille", nullable = false)
  String nomFamille;

  @Column(name = "prenom", nullable = false)
  String prenom;

  @Column(name = "sexe", nullable = false)
  String sexe;

  @Column(name = "date_naissance", nullable = false)
  LocalDate dateNaissance;

  @Column(name = "date_bapteme")
  LocalDate dateBapteme;

  @Column(name = "addresse", nullable = false)
  String addresse;

  @Column(name = "telephone")
  String telephone;

  @Column(name = "email")
  String email;

  @Column(name = "ancien")
  boolean ancien;

  @Column(name = "serviteur_ministeriel")
  boolean serviteurMinisteriel;

  @Column(name = "pionnier_permanent")
  boolean pionnierPermanent;

  @Column(name = "assigne")
  boolean assigne;

  @Column(name = "servir_dans_console")
  boolean servirDansConsole;

  @Column(name = "servir_dans_visio")
  boolean servirDansVisio;

  @Column(name = "servir_dans_microphone")
  boolean servirDansMicrophone;

  @Column(name = "ne_plus_servir")
  boolean nePeutPlusServir;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proclamateur")
  Set<AssignationConsole> assignationConsoles;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proclamateur")
  Set<AssignationMicrophone> assignationMicrophones;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proclamateur")
  Set<AssignationVisioConference> assignationVisioConferences;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Proclamateur that = (Proclamateur) o;
    return getProclamateurId() != null && Objects.equals(getProclamateurId(),
        that.getProclamateurId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
