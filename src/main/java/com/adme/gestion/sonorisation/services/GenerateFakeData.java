package com.adme.gestion.sonorisation.services;

import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import com.adme.gestion.sonorisation.models.TypeAssignation;
import com.adme.gestion.sonorisation.models.TypeProgramme;
import com.adme.gestion.sonorisation.port.ProclamateurPersistencePort;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class GenerateFakeData {

  ProclamateurPersistencePort proclamateurPersistencePort;
  Faker faker;
  GenerateProgrammeService generateProgrammeService;

  public GenerateFakeData(
      ProclamateurPersistencePort proclamateurPersistencePort,
      GenerateProgrammeService generateProgrammeService) {

    this.proclamateurPersistencePort = proclamateurPersistencePort;

    this.generateProgrammeService = generateProgrammeService;

    this.faker = new Faker();
  }

  private ProclamateurDomain buildProclamateur() {

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
        .servirDansConsole(new Random().nextBoolean())
        .servirDansEstrade(new Random().nextBoolean())
        .servirDansVisio(new Random().nextBoolean())
        .servirDansConsole(new Random().nextBoolean())
        .servirDansMicrophone(new Random().nextBoolean())
        .userCreate("system")
        .serviteurMinisteriel(false).build();
  }

  //@PostConstruct
  private void generateProclamateurs() {
    for (int i = 0; i < 40; i++) {
      ProclamateurDomain proclamateurDomain = buildProclamateur();
      proclamateurPersistencePort.saveOrUpdate(proclamateurDomain);
    }
  }

  @PostConstruct
  private void generateProgramme() {

    LocalDate dateStart = LocalDate.of(2023, Month.DECEMBER, 1);

    ProgrammeDomain programmeDomain = generateProgrammeService.saveProgramme(
        "Programme Novembre Decembre", dateStart, TypeProgramme.SONORISATION);

    log.info("Nbre assignations: {}", programmeDomain.getNomProgramme());

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
          .proclamateur(buildProclamateur()).build();
      assignations.add(assignationDomain);

    }
    return assignations;
  }
}
