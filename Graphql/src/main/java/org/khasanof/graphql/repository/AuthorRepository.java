package org.khasanof.graphql.repository;

import org.khasanof.graphql.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
