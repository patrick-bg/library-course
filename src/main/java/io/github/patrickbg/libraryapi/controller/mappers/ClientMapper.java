package io.github.patrickbg.libraryapi.controller.mappers;

import io.github.patrickbg.libraryapi.controller.dto.ClientDTO;
import io.github.patrickbg.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO dto);
}
