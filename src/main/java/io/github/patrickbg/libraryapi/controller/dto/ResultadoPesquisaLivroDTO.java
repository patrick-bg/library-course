package io.github.patrickbg.libraryapi.controller.dto;

import io.github.patrickbg.libraryapi.model.GeneroLivro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Resultado da Pesquisa de Livros")
public record ResultadoPesquisaLivroDTO(UUID id,
                                        String isbn,
                                        String titulo,
                                        LocalDate dataPublicacao,
                                        GeneroLivro genero,
                                        BigDecimal preco,
                                        AutorDTO autor
) {
}
