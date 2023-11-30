package com.adme.gestion.sonorisation.adapters.db.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapping;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "dateCreate", source = "dateCreate")
@Mapping(target = "userCreate", source = "userCreate")
@Mapping(target = "userUpdate", source = "userUpdate")
@Mapping(target = "dateUpdate", source = "dateUpdate")
public @interface  BaseEntityMapper {


}
