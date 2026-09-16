package io.github.patrickbg.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Erro de campo")
public record ErroCampo(String campo, String erro) {

}
