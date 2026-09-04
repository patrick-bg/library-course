package io.github.patrickbg.libraryapi.controller.mappers;

import io.github.patrickbg.libraryapi.controller.dto.UsuarioDTO;
import io.github.patrickbg.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
