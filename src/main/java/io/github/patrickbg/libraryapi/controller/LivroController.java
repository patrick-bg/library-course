package io.github.patrickbg.libraryapi.controller;

import io.github.patrickbg.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.patrickbg.libraryapi.controller.dto.ErroResposta;
import io.github.patrickbg.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.patrickbg.libraryapi.controller.mappers.LivroMapper;
import io.github.patrickbg.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.patrickbg.libraryapi.model.Livro;
import io.github.patrickbg.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = livroMapper.toEntity(dto);
        livroService.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id") UUID id) {
        return livroService.obterPorId(id).map(livro -> {
            var dto = livroMapper.toDto(livro);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
