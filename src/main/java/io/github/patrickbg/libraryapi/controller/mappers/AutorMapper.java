package io.github.patrickbg.libraryapi.controller.mappers;

import io.github.patrickbg.libraryapi.controller.dto.AutorDTO;
import io.github.patrickbg.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDto(Autor autor);
}
