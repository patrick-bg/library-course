package io.github.patrickbg.libraryapi.controller;

import io.github.patrickbg.libraryapi.controller.dto.ClientDTO;
import io.github.patrickbg.libraryapi.controller.mappers.ClientMapper;
import io.github.patrickbg.libraryapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public void salvar(@RequestBody ClientDTO dto){
        var client = clientMapper.toEntity(dto);
        clientService.salvar(client);
    }
}
