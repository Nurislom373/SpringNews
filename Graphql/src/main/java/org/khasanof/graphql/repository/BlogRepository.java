package org.khasanof.graphql.repository;

import org.khasanof.graphql.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
