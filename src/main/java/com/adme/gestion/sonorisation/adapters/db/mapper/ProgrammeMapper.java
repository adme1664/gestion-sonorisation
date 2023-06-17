package com.adme.gestion.sonorisation.adapters.db.mapper;

import com.adme.gestion.sonorisation.adapters.db.entities.Programme;
import com.adme.gestion.sonorisation.domain.ProgrammeDomain;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProgrammeMapper {

  ProgrammeDomain entityToDomain(Programme programme);

  @Mapping(source = "dateCommencement", target = "dateCommencement")
  @Mapping(source = "dateFin", target = "dateFin")
  @Mapping(source = "nomProgramme", target = "nomProgramme")
  Programme domainToEntity(ProgrammeDomain programmeDomain);

  List<ProgrammeDomain> mapToList(List<Programme> programmes);

}
