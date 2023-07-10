package com.adme.gestion.sonorisation.adapters.db.services;

import com.adme.gestion.sonorisation.adapters.db.entities.Assignation;
import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.adapters.db.repository.AssignationRepository;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AssignationImpl implements AssignationService{

  AssignationRepository assignationRepository;

  public AssignationImpl(AssignationRepository assignationRepository) {
    this.assignationRepository = assignationRepository;
  }


  @Override
  public Assignation saveOrUpdate(Assignation assignation) {
    return this.assignationRepository.save(assignation);
  }

  @Override
  public Assignation findById(UUID assignationId) {
    return this.assignationRepository.findById(assignationId).orElse(null);
  }

  @Override
  public List<Assignation> findAssignationParProgramme(Programme programme) {
    return this.assignationRepository.findAssignationByProgramme(programme);
  }
}
