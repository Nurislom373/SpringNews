package org.khasanof.clickhouse.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.khasanof.clickhouse.ClickhouseApplication;
import org.khasanof.clickhouse.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof.clickhouse.service
 * @since 6/9/25
 */
@SpringBootTest(classes = {ClickhouseApplication.class})
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    private Author author;

    @BeforeEach
    void initializeObjects() {
        author = createAuthor();
        authorService.create(author);
    }

    @AfterEach
    void destroyObjects() {
        authorService.delete(author);
    }

    @Test
    void firstTestFindAllAuthorsShouldSuccessfully() {
        List<Author> authors = authorService.findAll();

        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        assertEquals(1, authors.size());
    }

    private Author createAuthor() {
        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setName("John Doe");
        author.setEmail("john.doe@example.com");
        author.setCreatedAt(LocalDateTime.now());
        return author;
    }
}
