package com.adme.gestion.sonorisation.adapters.db.mapper;

import com.adme.gestion.sonorisation.adapters.db.entities.Assignation;
import com.adme.gestion.sonorisation.domain.AssignationDomain;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = IgnoreUnmappedConfig.class, uses = ProgrammeMapper.class)
public interface AssignationMapper {
  AssignationDomain entityToDomain(Assignation assignation);

  Assignation domainToEntity(AssignationDomain assignationDomain);

}
