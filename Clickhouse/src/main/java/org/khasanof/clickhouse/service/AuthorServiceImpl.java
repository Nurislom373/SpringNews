package org.khasanof.clickhouse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.clickhouse.model.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Nurislom
 * @see org.khasanof.clickhouse.service
 * @since 6/9/25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Author> findAll() {
        log.info("Find all authors");
        return jdbcTemplate.query(
                "SELECT * FROM authors",
                (ResultSet resultSet, int rowNum) -> new Author(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getObject("created_at", LocalDateTime.class)
                )
        );
    }

    @Override
    public void create(Author author) {
        log.info("Creating author: {}", author);
        jdbcTemplate.update("""
                        INSERT INTO authors (id, name, email, created_at)
                        VALUES (?, ?, ?, ?);
                        """,
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getCreatedAt()
        );
    }

    @Override
    public void update(Author author) {
        log.info("Updating author: {}", author);
        jdbcTemplate.update("""
                        ALTER TABLE authors 
                        UPDATE name = ?, email = ?, created_at = ? 
                        WHERE id = ?;
                        """,
                author.getName(),
                author.getEmail(),
                author.getCreatedAt(),
                author.getId()
        );
    }

    @Override
    public void delete(Author author) {
        log.info("Deleting author: {}", author);
        jdbcTemplate.update("ALTER TABLE authors DELETE WHERE id = ?", author.getId());
    }
}
