package com.adme.gestion.sonorisation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.adme.gestion.sonorisation.adapters.db.entities.AssignationMicrophone;
import com.adme.gestion.sonorisation.adapters.db.entities.ProgrammeSonorisation;
import com.adme.gestion.sonorisation.services.ProgrammeSonorisationService;
import com.adme.gestion.sonorisation.adapters.db.entities.AssignationConsole;
import com.adme.gestion.sonorisation.adapters.db.entities.AssignationVisioConference;
import com.adme.gestion.sonorisation.adapters.db.entities.Proclamateur;
import com.adme.gestion.sonorisation.adapters.db.repository.ProgrammeSonorisationRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProgrammeSonorisationServiceTest {

  @Mock
  ProgrammeSonorisationRepository programmeSonorisationRepository;

  @InjectMocks
  ProgrammeSonorisationService programmeSonorisationService;

  @Captor
  ArgumentCaptor<ProgrammeSonorisation> programmeSonorisationArgumentCaptor;

  UUID id = UUID.randomUUID();

  @Test
  @DisplayName("When save new programme sonorisation then "
      + "save new one ")
  void whenSaveNewProgrammeSonorisationThenReturn() {
    //given
    ProgrammeSonorisation programmeSonorisation = buildProgrammeSonorisation();

    when(programmeSonorisationRepository.save(programmeSonorisation)).thenReturn(
        programmeSonorisation);

    programmeSonorisationService.save(programmeSonorisation);

    verify(programmeSonorisationRepository).save(programmeSonorisationArgumentCaptor.capture());

    ProgrammeSonorisation saved = programmeSonorisationArgumentCaptor.getValue();

    assertEquals(saved.getNomProgramme(), programmeSonorisation.getNomProgramme());

    assertEquals(saved.getDateCommencement(), programmeSonorisation.getDateCommencement());

    assertEquals(saved.getDateFin(), programmeSonorisation.getDateFin());

  }

  @Test
  @DisplayName("When save existing progamme, search then update existing")
  void whenSaveProgrammeSearchForExistingOneThenUpdate(){
    //given
    ProgrammeSonorisation programme = buildProgrammeSonorisation();
    programme.setProgrammeId(id);
    programme.setAssignationMicrophones(buildAssignationMicrophone(programme));
    programme.setAssignationVisioConferences(buildSetVisioConference(programme));
    programme.setAssignationMicrophones(buildAssignationMicrophone(programme));

    ProgrammeSonorisation toUpdate = ProgrammeSonorisation.builder()
        .programmeId(id)
        .nomProgramme("Programme semaine 23")
        .dateCommencement(LocalDate.of(2023,4,20))
        .dateFin(LocalDate.of(2023,6,20))
        .build();
    toUpdate.setAssignationConsoles(buildAssignationConsole(toUpdate));
    toUpdate.setAssignationConsoles(buildAssignationConsole(toUpdate));
    toUpdate.setAssignationVisioConferences(buildSetVisioConference(toUpdate));

    when(programmeSonorisationRepository.findById(id)).thenReturn(Optional.of(programme));

    programmeSonorisationService.save(toUpdate);

    verify(programmeSonorisationRepository).save(programmeSonorisationArgumentCaptor.capture());

    var updated = programmeSonorisationArgumentCaptor.getValue();

    assertEquals(toUpdate.getProgrammeId(),updated.getProgrammeId());
    assertEquals(toUpdate.getDateCommencement(), updated.getDateCommencement());
    assertEquals(toUpdate.getNomProgramme(), updated.getNomProgramme());
    assertEquals(toUpdate.getDateFin(), updated.getDateFin());

  }

  private ProgrammeSonorisation buildProgrammeSonorisation() {
    return ProgrammeSonorisation.builder()
        .nomProgramme("Programme semaine 1")
        .dateCommencement(LocalDate.now())
        .dateFin(LocalDate.now())
        .build();
  }

  private Set<AssignationVisioConference> buildSetVisioConference(ProgrammeSonorisation programme) {
    Set<AssignationVisioConference> assignationVisioConferences = new HashSet<>();
    assignationVisioConferences.add(AssignationVisioConference.builder()
        .programme(programme)
        .dateAssignation(LocalDateTime.now())
        .proclamateur(buildProclamateur())
        .build());
    return assignationVisioConferences;
  }

  private Set<AssignationConsole> buildAssignationConsole(ProgrammeSonorisation programme) {
    Set<AssignationConsole> assignationConsoles = new HashSet<>();
    assignationConsoles.add(AssignationConsole.builder()
        .programme(programme)
        .dateAssignation(LocalDateTime.now())
        .proclamateur(buildProclamateur())
        .build());
    return assignationConsoles;
  }

  private Set<AssignationMicrophone> buildAssignationMicrophone(ProgrammeSonorisation programme) {
    Set<AssignationMicrophone> assignationMicrophones = new HashSet<>();
    assignationMicrophones.add(AssignationMicrophone.builder()
        .programme(programme)
        .dateAssignation(LocalDateTime.now())
        .proclamateur(buildProclamateur())
        .build());
    return assignationMicrophones;
  }

  private Proclamateur buildProclamateur() {
    return Proclamateur.builder()
        .proclamateurId(id)
        .addresse("addresse 1")
        .nomFamille("Adme")
        .prenom("Jean Jeff")
        .sexe("M")
        .dateNaissance(LocalDate.of(1987, 11, 1))
        .dateBapteme(LocalDate.of(2011, 7, 16))
        .email("email@gmail.com")
        .ancien(true)
        .assigne(true)
        .pionnierPermanent(true)
        .servirDansConsole(true)
        .servirDansVisio(true)
        .build();
  }

}
