package io.github.patrickbg.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Schema(name = "Usuario")
public record UsuarioDTO(
        @NotBlank(message = "Campo obrigatório")
        @Schema(name = "login")
        String login,
        @Email(message = "Inválido")
        @NotBlank(message = "Campo obrigatório")
        @Schema(name = "email")
        String email,
        @NotBlank(message = "Campo obrigatório")
        @Schema(name = "senha")
        String senha,
        @Schema(name = "roles")
        List<String> roles) {
}
