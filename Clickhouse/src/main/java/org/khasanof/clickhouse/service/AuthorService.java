package org.khasanof.clickhouse.service;

import org.khasanof.clickhouse.model.Author;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.clickhouse.service
 * @since 6/9/25
 */
public interface AuthorService {

    List<Author> findAll();

    void create(Author author);

    void update(Author author);

    void delete(Author author);
}
