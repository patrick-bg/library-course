package io.github.patrickbg.libraryapi.service;

import io.github.patrickbg.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.patrickbg.libraryapi.model.Autor;
import io.github.patrickbg.libraryapi.repository.AutorRepository;
import io.github.patrickbg.libraryapi.repository.LivroRepository;
import io.github.patrickbg.libraryapi.validator.AutorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, AutorValidator autorValidator, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
        this.livroRepository = livroRepository;
    }

    public Autor salvar(Autor autor) {
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar (Autor autor) {
        if(autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja no banco de dados.");
        }
        autorValidator.validar(autor);
       autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor) {
        if(possuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Não é permitido excluir um autor que possui livros cadastrados!");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisar(String nome, String nacionalidade) {
        //Filtro por nome e nacionalidade
        if(nome != null && nacionalidade != null) {
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        //Filtro por nome
        if(nome != null){
            return autorRepository.findByNome(nome);
        }

        //Filtro por nacionalidade
        if(nacionalidade != null){
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        //Pesquisa geral
        return autorRepository.findAll();
    }

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }

}
