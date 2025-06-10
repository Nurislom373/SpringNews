package org.khasanof.service;

import org.khasanof.model.Author;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 6/9/25
 */
public interface AuthorService {

    List<Author> findAll();

    void create(Author author);

    void update(Author author);

    void delete(Author author);
}
