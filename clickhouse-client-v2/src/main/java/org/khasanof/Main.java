package org.khasanof;

import org.khasanof.factory.ClickhouseClientFactory;
import org.khasanof.factory.ClickhouseClientFactoryImpl;
import org.khasanof.model.Author;
import org.khasanof.service.AuthorService;
import org.khasanof.service.AuthorServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 6/9/25
 */
public class Main {

    public static void main(String[] args) {
        ClickhouseClientFactory factory = new ClickhouseClientFactoryImpl();
        AuthorService authorService = new AuthorServiceImpl(factory);

        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setName("Khasanof");
        author.setEmail("khasanof@khasanof.com");
        author.setCreatedAt(LocalDateTime.now());

        authorService.create(author); // create

        List<Author> authors = authorService.findAll(); // create
        System.out.println("authors = " + authors);

        author.setName("KhasanofUpdated");
        authorService.update(author); // update

        authorService.delete(author); // delete
    }
}
