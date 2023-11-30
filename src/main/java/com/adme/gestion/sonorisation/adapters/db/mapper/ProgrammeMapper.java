package com.adme.gestion.sonorisation.adapters.db.mapper;

import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = IgnoreUnmappedConfig.class)
public interface ProgrammeMapper {

  @Mapping(source = "dateCommencement", target = "dateCommencement")
  @Mapping(source = "dateFin", target = "dateFin")
  @Mapping(source = "nomProgramme", target = "nomProgramme")
  @BaseEntityMapper
  ProgrammeDomain entityToDomain(Programme programme);

  @Mapping(source = "dateCommencement", target = "dateCommencement")
  @Mapping(source = "dateFin", target = "dateFin")
  @Mapping(source = "nomProgramme", target = "nomProgramme")
  Programme domainToEntity(ProgrammeDomain programmeDomain);

}
