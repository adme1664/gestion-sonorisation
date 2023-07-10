package com.adme.gestion.sonorisation.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.adapters.db.mapper.ProgrammeMapper;
import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import com.adme.gestion.sonorisation.models.TypeAssignation;
import com.adme.gestion.sonorisation.models.TypeProgramme;
import com.adme.gestion.sonorisation.port.AssignationPersistencePort;
import com.adme.gestion.sonorisation.port.ProgrammePersistencePort;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class GenerateFakeData {

  ProgrammePersistencePort programmePersistencePort;
  AssignationPersistencePort assignationPersistencePort;
  ProgrammeMapper programmeMapper;
  Faker faker;

  public GenerateFakeData(ProgrammePersistencePort programmePersistencePort,
      AssignationPersistencePort assignationPersistencePort,
      ProgrammeMapper programmeMapper) {
    this.programmePersistencePort = programmePersistencePort;
    this.assignationPersistencePort = assignationPersistencePort;
    this.programmeMapper = programmeMapper;
    this.faker = new Faker();
  }

  //@PostConstruct
  private ProclamateurDomain generateProclamateurs() {

    return ProclamateurDomain.builder()
        .nomFamille(faker.name().firstName())
        .prenom(faker.name().lastName())
        .sexe("M")
        .dateNaissance(
            faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        .dateBapteme(
            faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        .addresse(faker.address().fullAddress())
        .telephone(faker.phoneNumber().cellPhone())
        .email(faker.internet().emailAddress())
        .ancien(false)
        .userCreate("system")
        .serviteurMinisteriel(false).build();
  }

  @PostConstruct
  private void generateProgramme() {
    ProgrammeDomain programme = ProgrammeDomain.builder()
        .programmeId(UUID.randomUUID())
        .typeProgramme(TypeProgramme.SONORISATION)
        .nomProgramme("Programme mois juin - juillet")
        .dateCommencement(LocalDate.now().plusWeeks(2))
        .dateFin(LocalDate.now().plusMonths(2))
        .build();

    var assignations = buildListAssignation(programme);
    var saved = (ProgrammeDomain) programmePersistencePort.saveOrUpdate(programme);
    assignations.forEach(
        assignation -> {
          assignation.setProgramme(saved);
          assignationPersistencePort.saveOrUpdate(assignation);
        });
  }

  private List<AssignationDomain> buildListAssignation(ProgrammeDomain programmeDomain) {
    LocalDate dateAssignation = LocalDate.now();
    List<AssignationDomain> assignations = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      dateAssignation = dateAssignation.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
      AssignationDomain assignationDomain = AssignationDomain.builder()
          .programme(programmeDomain)
          .typeAssignation(TypeAssignation.MICROPHONE)
          .dateAssignation(dateAssignation)
          .proclamateur(generateProclamateurs()).build();
      assignations.add(assignationDomain);

    }
    return assignations;
  }
}
