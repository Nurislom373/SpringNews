package org.khasanof.graphql.service;

import lombok.RequiredArgsConstructor;
import org.khasanof.graphql.dto.blog.BlogCreateDTO;
import org.khasanof.graphql.dto.blog.BlogUpdateDTO;
import org.khasanof.graphql.entity.Author;
import org.khasanof.graphql.entity.Blog;
import org.khasanof.graphql.repository.AuthorRepository;
import org.khasanof.graphql.repository.BlogRepository;
import org.khasanof.graphql.utils.BaseUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository repository;
    private final AuthorRepository authorRepository;

    public List<Blog> list() {
        return repository.findAll();
    }

    public Blog findById(Integer id) {
        Assert.notNull(id, "id is required not null!");
        return repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("id not found");
        });
    }

    public Blog save(BlogCreateDTO dto) {
        Assert.notNull(dto, "dto is required not null!");
        Blog blog = BaseUtils.dtoToBlog(dto, new Blog());
        blog.setAuthor(new Author(3, "fdsfds", 12));
        return repository.save(blog);
    }

    public void update(BlogUpdateDTO dto) {
        Assert.notNull(dto, "dto is required not null!");
        Blog blog = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new RuntimeException("id not found");
        });
        Blog blogUpd = BaseUtils.dtoToBlog(dto, blog);
        repository.save(blogUpd);
    }

    public void delete(Integer id) {
        Assert.notNull(id, "id is required not null!");
        repository.deleteById(id);
    }
}
