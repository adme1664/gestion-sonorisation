package com.adme.gestion.sonorisation.services;

import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import com.adme.gestion.sonorisation.models.TypeAssignation;
import com.adme.gestion.sonorisation.models.TypeProgramme;
import com.adme.gestion.sonorisation.port.AssignationPort;
import com.adme.gestion.sonorisation.port.ProclamateurPersistencePort;
import com.adme.gestion.sonorisation.port.ProgrammePersistencePort;
import com.adme.gestion.sonorisation.utils.Constants;
import com.adme.gestion.sonorisation.utils.Utils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class GenerateProgrammeService {

  final AssignationPort assignationPort;
  final ProclamateurPersistencePort proclamateurPersistencePort;
  final ProgrammePersistencePort programmePersistencePort;
  final int dureeProgramme;
  final List<AssignationDomain> assignationsToSave = new ArrayList<>();
  final List<AssignationDomain> assignationAlreadyExists = new ArrayList<>();

  public GenerateProgrammeService(
      AssignationPort assignationPort,
      ProclamateurPersistencePort proclamateurPersistencePort,
      ProgrammePersistencePort programmePersistencePort,
      @Value("${programme.duree}") int dureeProgramme) {
    this.assignationPort = assignationPort;
    this.proclamateurPersistencePort = proclamateurPersistencePort;
    this.programmePersistencePort = programmePersistencePort;
    this.dureeProgramme = dureeProgramme;

  }

  public ProgrammeDomain saveProgramme(
      String nonProgramme,
      LocalDate dateCommencement,
      TypeProgramme typeProgramme) {

    ProgrammeDomain programme =
        (ProgrammeDomain) this.programmePersistencePort
            .saveOrUpdate(buildProgramme(nonProgramme, dateCommencement, typeProgramme));

    buildMonthlyAssignation(programme);

    assignationsToSave.forEach(this.assignationPort::saveOrUpdate);

    return programme;

  }


  /**
   * Creer un programme pour une duree de 2 mois
   *
   * @param nomProgramme     Le nom du programme
   * @param dateCommencement La date de commencement
   * @param typeProgramme    Le {@linkplain TypeProgramme}
   * @return {@linkplain ProgrammeDomain}
   */
  public ProgrammeDomain buildProgramme(String nomProgramme, @NonNull LocalDate dateCommencement,
      TypeProgramme typeProgramme) {

    LocalDate dateFin = dateCommencement.plusMonths(dureeProgramme);

    return ProgrammeDomain.builder()
        .nomProgramme(nomProgramme)
        .typeProgramme(typeProgramme)
        .dateCommencement(dateCommencement)
        .dateFin(dateFin).build();

  }

  private List<ProclamateurDomain> listOfProclamateursAlreadyAssignedInAService(
      List<AssignationDomain> existingAssignations, TypeAssignation typeAssignation) {
    return existingAssignations.stream().filter(
            assignationDomain -> assignationDomain.getTypeAssignation().equals(typeAssignation))
        .map(AssignationDomain::getProclamateur).toList();
  }

  private void buildAssignationList(AssignationDomain assignationDomain) {
    assignationsToSave.add(assignationDomain);
    assignationAlreadyExists.add(assignationDomain);
  }

  /**
   * Creer les assignations pour le mois par programme
   *
   * @param programmeDomain {@linkplain ProgrammeDomain} Le programme qu'on est en train de
   *                        construire
   */
  public void buildMonthlyAssignation(ProgrammeDomain programmeDomain) {

    List<LocalDate> days = Utils
        .buildWeeklyDate(programmeDomain.getDateCommencement(), programmeDomain.getDateFin());

    days.forEach(day -> {
      //Add visio assignation
      buildWeeklyVisioAssignation(assignationsToSave, programmeDomain, day);

      //Add microphone assignation
      buildWeeklyMicrophoneAssignation(assignationsToSave, programmeDomain, day);

      //add console assignation
      buildWeeklyConsoleAssignation(assignationsToSave, programmeDomain, day);
    });

  }


  /**
   * Construire la liste des assignations des personnes dans le service de visioconference
   *
   * @param existingAssignations la {@linkplain List} des assignations existantes
   * @param programme            Le {@linkplain ProgrammeDomain} en cours
   * @param dateAssignation      La date d'assignation
   */
  public void buildWeeklyVisioAssignation(
      List<AssignationDomain> existingAssignations,
      ProgrammeDomain programme, LocalDate dateAssignation) {

    List<UUID> proclamateurDomainsExisted = listOfProclamateursAlreadyAssignedInAService(
        assignationAlreadyExists, TypeAssignation.VISIO_CONFERENCE)
        .stream()
        .map(ProclamateurDomain::getProclamateurId)
        .toList();

    List<ProclamateurDomain> proclamateursInVisios =
        proclamateurPersistencePort.getAllProclamateurPeutServirDansVisio(Constants.PEUT_SERVIR);

    List<ProclamateurDomain> proclamateursNotAssigned = proclamateursInVisios.stream()
        .filter(prcmt -> !proclamateurDomainsExisted.contains(prcmt.getProclamateurId())).toList();

    //Si tous les proclamateurs sont deja assignes, reassigne les pour les semaines suivantes
    if (proclamateursNotAssigned.isEmpty()) {
      proclamateursNotAssigned = reAssigneProclamateursToService(
          TypeAssignation.VISIO_CONFERENCE,
          existingAssignations,
          dateAssignation,
          proclamateursInVisios);
    }

    if (!proclamateursNotAssigned.isEmpty()) {
      for (ProclamateurDomain proclamateur : proclamateursNotAssigned) {
        if (notAlreadyAssignedInThisDate(existingAssignations, proclamateur, dateAssignation)) {
          buildAssignationList(
              buildAssignation(
                  programme,
                  dateAssignation,
                  proclamateur,
                  TypeAssignation.VISIO_CONFERENCE)
          );
          break;
        }

      }
    }

  }

  /**
   * Creer les assignations de console
   *
   * @param existingAssignations La {@linkplain List } des assignaitons existantes
   * @param programme            Le {@linkplain ProgrammeDomain} Le programme
   * @param dateAssignation      La date d'assignation
   */
  public void buildWeeklyConsoleAssignation(
      List<AssignationDomain> existingAssignations,
      ProgrammeDomain programme, LocalDate dateAssignation) {

    List<UUID> proclamateurDomainsExisted = listOfProclamateursAlreadyAssignedInAService(
        assignationAlreadyExists, TypeAssignation.CONSOLE)
        .stream()
        .map(ProclamateurDomain::getProclamateurId)
        .toList();

    List<ProclamateurDomain> proclamateursConsoles = proclamateurPersistencePort
        .getAllProclamateurPeutServirDansConsole(Constants.PEUT_SERVIR);

    List<ProclamateurDomain> proclamateursNotAssigned = proclamateursConsoles.stream()
        .filter(prcmt -> !proclamateurDomainsExisted.contains(prcmt.getProclamateurId())).toList();

    //Si tous les proclamateurs sont deja assignes, reassigne les pour les semaines suivantes
    if (proclamateursNotAssigned.isEmpty()) {
      proclamateursNotAssigned = reAssigneProclamateursToService(
          TypeAssignation.CONSOLE,
          existingAssignations,
          dateAssignation,
          proclamateursConsoles);
    }

    if (!proclamateursNotAssigned.isEmpty()) {
      for (ProclamateurDomain proclamateur : proclamateursNotAssigned) {
        if (notAlreadyAssignedInThisDate(existingAssignations, proclamateur, dateAssignation)) {
          buildAssignationList(
              buildAssignation(
                  programme,
                  dateAssignation,
                  proclamateur,
                  TypeAssignation.CONSOLE)
          );
          break;
        }

      }
    }

  }

  /**
   * Construire les assignations de microphones
   *
   * @param existingAssignations a {@linkplain List} of {@linkplain AssignationDomain}
   * @param programmeDomain      le {@linkplain ProgrammeDomain} en cours
   * @param dateAssignation      La date de l'assignation
   */
  public void buildWeeklyMicrophoneAssignation(
      List<AssignationDomain> existingAssignations, ProgrammeDomain programmeDomain,
      LocalDate dateAssignation) {

    //Recherche les proclamateurs qui ne sont pas encore assignes
    List<UUID> proclamateurDomainsExisted = listOfProclamateursAlreadyAssignedInAService(
        assignationAlreadyExists, TypeAssignation.MICROPHONE)
        .stream()
        .map(ProclamateurDomain::getProclamateurId)
        .toList();

    //Recherche les proclamateurs qui sont seulement assignés dans le service de microphone
    List<ProclamateurDomain> proclamateursMicrophones = proclamateurPersistencePort
        .getAllProclamateurPeutServirDansMicrophone(Constants.PEUT_SERVIR);

    //Prendre les proclamateurs qui n'ont pas ete assignes
    List<ProclamateurDomain> proclamateursNotAssigned = proclamateursMicrophones.stream()
        .filter(prcmt -> !proclamateurDomainsExisted.contains(prcmt.getProclamateurId())).toList();

    //Si tous les proclamateurs sont deja assignes, reassigne les pour les semaines suivantes
    if (proclamateursNotAssigned.isEmpty()) {
      proclamateursNotAssigned = reAssigneProclamateursToService(
          TypeAssignation.MICROPHONE,
          existingAssignations,
          dateAssignation,
          proclamateursMicrophones);
    }

    if (!proclamateursNotAssigned.isEmpty()) {
      /*
    Tous les samedis, on a 4 personnes assignées dans le service de microphone
     */
      if (dateAssignation.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
        int nbrAssingation = 0;
        for (ProclamateurDomain proclamateur : proclamateursNotAssigned) {
          if (notAlreadyAssignedInThisDate(existingAssignations, proclamateur, dateAssignation)) {
            buildAssignationList(
                buildAssignation(
                    programmeDomain,
                    dateAssignation,
                    proclamateur,
                    TypeAssignation.MICROPHONE)
            );
            nbrAssingation++;
          }
          if (nbrAssingation == 4) {
            break;
          }
        }

      } else {
        //Tous les lundis, on prend 2 personnes
        if (dateAssignation.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
          int nbrAssingation = 0;
          for (ProclamateurDomain proclamateur : proclamateursNotAssigned) {
            if (notAlreadyAssignedInThisDate(existingAssignations, proclamateur, dateAssignation)) {
              buildAssignationList(
                  buildAssignation(
                      programmeDomain,
                      dateAssignation,
                      proclamateur,
                      TypeAssignation.MICROPHONE));
              nbrAssingation++;
            }
            if (nbrAssingation == 2) {
              break;
            }
          }
        }
      }
    }
  }

  /**
   * ReAssigne les proclamateurs dans le prochain
   *
   * @param existingAssignations    liste des assignations
   * @param dateAssignation         Date de l'assignation
   * @param proclamateursInServices liste des proclamateurs qui servent dans un service
   * @return Liste des proclamateurs non assignes
   */
  private List<ProclamateurDomain> reAssigneProclamateursToService(
      TypeAssignation typeAssignation,
      List<AssignationDomain> existingAssignations,
      LocalDate dateAssignation,
      List<ProclamateurDomain> proclamateursInServices) {

    assignationAlreadyExists.clear();

    //On retire les proclamateurs qui ont ete assignes la derniere semaine
    LocalDate lastWeekAssignation = dateAssignation.minusWeeks(1);

    List<UUID> assignedProclamateursLastWeek = existingAssignations.stream()
        .filter(assgn -> assgn.getDateAssignation().isEqual(lastWeekAssignation)
            && assgn.getTypeAssignation().equals(typeAssignation))
        .map(AssignationDomain::getProclamateur)
        .map(ProclamateurDomain::getProclamateurId)
        .toList();

    return proclamateursInServices.stream()
        .filter(prclmt -> !assignedProclamateursLastWeek.contains(prclmt.getProclamateurId()))
        .toList();
  }

  /**
   * Build une nouvelle assignation
   *
   * @param programme       {@linkplain ProgrammeDomain}Le programme en cours
   * @param dateAssignation {@linkplain LocalDate} La date d'assignaiton
   * @param proclamateur    {@linkplain com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur}
   *                        Le proclamateur
   * @param typeAssignation {@linkplain TypeAssignation} Le type de l'assignation
   * @return {@linkplain} l'ssignation
   */
  private AssignationDomain buildAssignation(
      ProgrammeDomain programme,
      LocalDate dateAssignation,
      ProclamateurDomain proclamateur,
      TypeAssignation typeAssignation) {

    return AssignationDomain.builder()
        .dateAssignation(dateAssignation)
        .programme(programme)
        .proclamateur(proclamateur)
        .typeAssignation(typeAssignation)
        .build();
  }

  private boolean notAlreadyAssignedInThisDate(List<AssignationDomain> existingAssignations,
      ProclamateurDomain proclamateur,
      LocalDate dateAssignation) {

    List<AssignationDomain> list =
        existingAssignations.stream().filter(assignationDomain ->
                assignationDomain.getDateAssignation().equals(dateAssignation)
                    && assignationDomain.getProclamateur().getProclamateurId()
                    .equals(proclamateur.getProclamateurId()))
            .toList();
    return list.isEmpty();
  }

}
