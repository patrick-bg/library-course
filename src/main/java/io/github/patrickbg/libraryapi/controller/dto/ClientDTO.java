package io.github.patrickbg.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Client")
public record ClientDTO(
        @NotBlank(message = "Campo obrigatório")
        @Schema(name = "clientId")
        String clientId,
        @NotBlank(message = "Campo obrigatório")
        @Schema(name = "clientSecret")
        String clientSecret,
        @NotBlank(message = "Campo obrigatório")
        @Schema(name = "redirectURI")
        String redirectURI,
        @Schema(name = "scope")
        String scope
) {
}
