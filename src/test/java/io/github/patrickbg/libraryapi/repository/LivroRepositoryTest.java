package io.github.patrickbg.libraryapi.repository;

import io.github.patrickbg.libraryapi.model.Autor;
import io.github.patrickbg.libraryapi.model.GeneroLivro;
import io.github.patrickbg.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTeste(){
        Livro livro = new Livro();
        livro.setIsbn("45876-25728");
        livro.setPreco(BigDecimal.valueOf(84));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Darwin");
        livro.setDataPublicacao(LocalDate.of(1950, 2, 5));

        Autor autor = autorRepository
                .findById(UUID.fromString("ef3dbc7b-eb20-48ef-8c22-6c0789e4bb0f"))
                .orElse(null);


        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTeste(){
        Livro livro = new Livro();
        livro.setIsbn("45876-25728");
        livro.setPreco(BigDecimal.valueOf(52));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Darwin");
        livro.setDataPublicacao(LocalDate.of(1950, 2, 5));

        Autor autor = new Autor();
        autor.setNome("Charles");
        autor.setNacionalidade("Britânica");
        autor.setDataNascimento(LocalDate.of(1890, 8, 22));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        var livroParaAtualizar = repository.findById(UUID.fromString("0f44a45e-fa91-4a1a-b439-0e6d8bead1c9")).orElse(null);

        UUID idAutor = UUID.fromString("2c13132e-646e-4ff7-a4c6-71d2a099773e");
        Autor maria = autorRepository.findById(UUID.fromString("2c13132e-646e-4ff7-a4c6-71d2a099773e")).orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("0f44a45e-fa91-4a1a-b439-0e6d8bead1c9");
        repository.deleteById(id);
    }

    @Test
    void buscarLivroTest(){
        UUID id = UUID.fromString("ca59a452-af4d-4e49-aa02-04ecf298e153");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println (livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println (livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("Metal Gear");
        lista.forEach(System.out::println);

    }

    @Test
    void pesquisaPorIsbnTest(){
        Optional<Livro> livro = repository.findByIsbn("55899-66489");
        livro.ifPresent(System.out::println);
    }

    @Test
    void pesquisaPorTituloAndPrecoTest(){
        var preco = BigDecimal.valueOf(150.00);
        var tituloPesquisa = "Death Stranding";
        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void titulosNaoRepetidosDosLivros(){
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }
    @Test
    void listarGenerosDeLivrosAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.FICCAO,"dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionalParamTest(){
        var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.FICCAO,"dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest(){
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacaoTest(){
        repository.updateDataPublicacao(LocalDate.of(2010,1,1));
    }
}