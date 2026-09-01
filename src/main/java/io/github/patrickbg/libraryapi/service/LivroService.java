package io.github.patrickbg.libraryapi.service;

import io.github.patrickbg.libraryapi.model.GeneroLivro;
import io.github.patrickbg.libraryapi.model.Livro;
import io.github.patrickbg.libraryapi.repository.LivroRepository;
import io.github.patrickbg.libraryapi.repository.specs.LivroSpecs;
import io.github.patrickbg.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroValidator livroValidator;

    public Livro salvar(Livro livro) {
        livroValidator.validar(livro);
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro) {
        livroRepository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn,
            String titulo,
            String nomeAutor,
            GeneroLivro genero,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhoPagina
    ) {

        Specification<Livro> specification = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (isbn != null) {
            specification = specification.and(LivroSpecs.isbnEquals(isbn));
        }

        if (titulo != null) {
            specification = specification.and(LivroSpecs.tituloLike(titulo));
        }

        if (genero != null) {
            specification = specification.and(LivroSpecs.generoEquals(genero));
        }

        if (anoPublicacao != null) {
            specification = specification.and(LivroSpecs.anoPublicacaoEquals(anoPublicacao));
        }

        if (nomeAutor != null) {
            specification = specification.and(LivroSpecs.nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina,tamanhoPagina);

        return livroRepository.findAll(specification,pageRequest);
    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro já esteja salvo na base!");
        }
        livroValidator.validar(livro);
        livroRepository.save(livro);
    }
}
