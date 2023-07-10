package com.adme.gestion.sonorisation.services;

import com.adme.gestion.sonorisation.domain.AssignationDomain;
import com.adme.gestion.sonorisation.domain.ProclamateurDomain;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import com.adme.gestion.sonorisation.models.TypeAssignation;
import com.adme.gestion.sonorisation.models.TypeProgramme;
import com.adme.gestion.sonorisation.port.AssignationPersistencePort;
import com.adme.gestion.sonorisation.port.ProclamateurPersistencePort;
import com.adme.gestion.sonorisation.port.ProgrammePersistencePort;
import com.adme.gestion.sonorisation.utils.Constants;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GenererProgrammeService {

  AssignationPersistencePort assignationPersistencePort;
  ProclamateurPersistencePort proclamateurPersistencePort;
  ProgrammePersistencePort programmePersistencePort;
  int dureeProgramme;

  public GenererProgrammeService(
      AssignationPersistencePort assignationPersistencePort,
      ProclamateurPersistencePort proclamateurPersistencePort,
      ProgrammePersistencePort programmePersistencePort,
      @Value("${programme.duree}") int dureeProgramme) {
    this.assignationPersistencePort = assignationPersistencePort;
    this.proclamateurPersistencePort = proclamateurPersistencePort;
    this.programmePersistencePort = programmePersistencePort;
    this.dureeProgramme = dureeProgramme;
  }

  public ProgrammeDomain buildProgramme(String nomProgramme, @NonNull LocalDate dateCommencement,
      TypeProgramme typeProgramme) {

    LocalDate dateFin = dateCommencement.plusMonths(dureeProgramme);
    return ProgrammeDomain.builder()
        .nomProgramme(nomProgramme)
        .typeProgramme(typeProgramme)
        .dateCommencement(dateCommencement)
        .dateFin(dateFin).build();

  }

  public List<AssignationDomain> buildWeeklyAssignation(ProgrammeDomain programmeDomain) {

    ProgrammeDomain lastProgramme = programmePersistencePort.getLastProgrammeByType(
        programmeDomain.getTypeProgramme().name());

    //Get the latest assignations for the last 2 weeks


    return null;
  }

  public List<AssignationDomain> buildWeeklyVisioAssignation(
      List<AssignationDomain> builtAssignationVisios,
      ProgrammeDomain programme, LocalDate dateAssignation) {

    List<AssignationDomain> assignationVisio = new ArrayList<>();

    List<ProclamateurDomain> proclamateursInVisios = proclamateurPersistencePort
        .getAllProclamateurPeutServirDansVisio(Constants.PEUT_SERVIR);
    for (ProclamateurDomain proclamateur : proclamateursInVisios) {
      if (verifieProclamateurPasAssigneDeuxSemainesAVenir(builtAssignationVisios, proclamateur,
          dateAssignation)) {
        assignationVisio.add(buildAssignation(programme, dateAssignation, proclamateur));
        break;
      }
    }

    return assignationVisio;

  }

  public List<AssignationDomain> buildWeeklyMicrophoneAssignation(
      List<AssignationDomain> builtAssignations, ProgrammeDomain programmeDomain,
      LocalDate dateAssignation) {
    List<AssignationDomain> assignationDomains = new ArrayList<>();
    List<ProclamateurDomain> proclamateurs = proclamateurPersistencePort
        .getAllProclamateurPeutServirDansMicrophone(Constants.PEUT_SERVIR);

    if (dateAssignation.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
      for (ProclamateurDomain proclamateur : proclamateurs) {
        if (verifieProclamateurPasAssigneDeuxSemainesAVenir(builtAssignations, proclamateur,
            dateAssignation)) {
          assignationDomains.add(buildAssignation(programmeDomain, dateAssignation, proclamateur));
        }
        if (assignationDomains.size() == 4) {
          break;
        }
      }
    } else {

      if (dateAssignation.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
        for (ProclamateurDomain proclamateur : proclamateurs) {
          if (verifieProclamateurPasAssigneDeuxSemainesAVenir(builtAssignations, proclamateur,
              dateAssignation)) {
            assignationDomains.add(
                buildAssignation(programmeDomain, dateAssignation, proclamateur));
          }
          if (assignationDomains.size() == 2) {
            break;
          }
        }
      }
    }
    return assignationDomains;
  }

  private AssignationDomain buildAssignation
      (ProgrammeDomain programmeDomain,
          LocalDate dateAssignation, ProclamateurDomain proclamateur) {
    return AssignationDomain.builder()
        .dateAssignation(dateAssignation)
        .programme(programmeDomain)
        .proclamateur(proclamateur)
        .typeAssignation(TypeAssignation.MICROPHONE).build();
  }

  private boolean verifieProclamateurPasAssigneDeuxSemainesAVenir(
      List<AssignationDomain> assignations, ProclamateurDomain proclamateur,
      LocalDate dateAssignation) {
    return assignations.stream().filter(
            isAssignedTwoWeekBefore(proclamateur, dateAssignation))
        .toList().size() != 0;

  }

  private Predicate<AssignationDomain> isAssignedTwoWeekBefore(
      ProclamateurDomain proclamateur, LocalDate dateAssignation) {
    return assignation ->
        assignation.getProclamateur().getProclamateurId().equals(proclamateur.getProclamateurId())
            && assignation.getDateAssignation().isBefore(dateAssignation)
            && assignation.getDateAssignation()
            .isAfter(assignation.getDateAssignation().minusWeeks(2));
  }


}
