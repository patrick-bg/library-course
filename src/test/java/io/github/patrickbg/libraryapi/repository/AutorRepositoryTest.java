package io.github.patrickbg.libraryapi.repository;

import io.github.patrickbg.libraryapi.model.Autor;
import io.github.patrickbg.libraryapi.model.GeneroLivro;
import io.github.patrickbg.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1975, 10, 10));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("bd5a773f-4168-4a72-8084-7483d7865f4b");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960,10,10));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void buscarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("a49d18f7-5b1e-4043-9dc9-57ea26bd96c5");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("396aeaea-efaf-4f7c-bd1f-c3edf2b9d1c7");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Kojima");
        autor.setNacionalidade("Japonesa");
        autor.setDataNascimento(LocalDate.of(1969, 5, 22));

        Livro livro = new Livro();
        livro.setIsbn("55899-66489");
        livro.setPreco(BigDecimal.valueOf(150));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Death Stranding");
        livro.setDataPublicacao(LocalDate.of(2019, 11, 23));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("33432-455345");
        livro2.setPreco(BigDecimal.valueOf(150));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Metal Gear");
        livro2.setDataPublicacao(LocalDate.of(1999, 7, 15));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

       livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("ef3dbc7b-eb20-48ef-8c22-6c0789e4bb0f");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);


        autor.getLivros().forEach(System.out::println);
    }

}
