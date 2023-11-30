package com.adme.gestion.sonorisation.adapters.db.mapper;

import com.adme.gestion.sonorisation.adapters.db.entities.Assignation;
import com.adme.gestion.sonorisation.domain.AssignationDomain;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AssignationMapper {

  @Autowired
  ProgrammeMapper programmeMapper;

  @Autowired
  ProclamateurMapper proclamateurMapper;

//  @Mapping(target = "programme", source = "programme")
//  @Mapping(target = "proclamateur", source = "proclamateur")
  @Named("entityToDomain")
  @Mapping(target = "dateAssignation", source = "dateAssignation")
  @Mapping(target = "typeAssignation", source = "typeAssignation")
  @BaseEntityMapper
  public abstract AssignationDomain entityToDomain(Assignation assignation);

//  @Mapping(target = "programme", source = "programme")
//  @Mapping(target = "proclamateur", source = "proclamateur")
  @Mapping(target = "dateAssignation", source = "dateAssignation")
  @Mapping(target = "typeAssignation", source = "typeAssignation")
 public abstract Assignation domainToEntity(AssignationDomain assignationDomain);

  public AssignationDomain entityToFullDomain(Assignation assignation) {
    AssignationDomain assignationDomain = entityToDomain(assignation);
    assignationDomain.setProgramme(this.programmeMapper.entityToDomain(assignation.getProgramme()));
    assignationDomain
        .setProclamateur(this.proclamateurMapper.entityToDomain(assignation.getProclamateur()));
    return assignationDomain;
  }

}
