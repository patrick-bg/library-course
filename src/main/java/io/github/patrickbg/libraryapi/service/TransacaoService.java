package io.github.patrickbg.libraryapi.service;

import io.github.patrickbg.libraryapi.model.Autor;
import io.github.patrickbg.libraryapi.model.GeneroLivro;
import io.github.patrickbg.libraryapi.model.Livro;
import io.github.patrickbg.libraryapi.repository.AutorRepository;
import io.github.patrickbg.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    // livro (titulo,..., nome_arquivo) -> id.png
    @Transactional
    public void salvarLivroComFoto(){
        // salva o livro
        // repository.save(livro);

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salvar foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png");
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("c24d7393-6371-4828-bd02-45f9a208b988")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024,6,1));
    }

    @Transactional
    public void executar(){

        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Britânica");
        autor.setDataNascimento(LocalDate.of(1890, 8, 22));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("45876-25728");
        livro.setPreco(BigDecimal.valueOf(52));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Teste Evolução");
        livro.setDataPublicacao(LocalDate.of(1950, 2, 5));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback!");
        }
    }
}
