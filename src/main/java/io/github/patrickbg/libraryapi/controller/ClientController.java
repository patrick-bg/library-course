package io.github.patrickbg.libraryapi.controller;

import io.github.patrickbg.libraryapi.controller.dto.ClientDTO;
import io.github.patrickbg.libraryapi.controller.mappers.ClientMapper;
import io.github.patrickbg.libraryapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Tag(name = "Clients")
@Slf4j
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar", description = "Cadastrar novo Client.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "409", description = "Client já cadastrado")
    })
    public void salvar(@RequestBody ClientDTO dto){
        log.info("Registrando novo Client: {} com scope: {} ", dto.clientId(), dto.scope());
        var client = clientMapper.toEntity(dto);
        clientService.salvar(client);
    }
}
