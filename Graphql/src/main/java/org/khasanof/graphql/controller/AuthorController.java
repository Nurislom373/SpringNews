package org.khasanof.graphql.controller;

import org.khasanof.graphql.dto.author.AuthorCreateDTO;
import org.khasanof.graphql.dto.author.AuthorUpdateDTO;
import org.khasanof.graphql.entity.Author;
import org.khasanof.graphql.repository.AuthorRepository;
import org.khasanof.graphql.response.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @SchemaMapping(typeName = "Query", value = "allAuthors")
    public List<Author> findAll() {
        return repository.findAll();
    }

    @SchemaMapping(typeName = "Query", value = "findByIdAuthor")
    public Author findOne(@Argument Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    @SchemaMapping(typeName = "Mutation", value = "updateAuthor")
    public Response update(@Argument AuthorUpdateDTO dto) {
        Author author = repository.findById(dto.getId()).orElseThrow();
        BeanUtils.copyProperties(dto, author, "id");
        repository.save(author);
        return new Response("Successfully Updated - Author", HttpStatus.CREATED.value());
    }

    @SchemaMapping(typeName = "Mutation", value = "saveAuthor")
    public Response save(@Argument AuthorCreateDTO dto) {
        Author author = new Author();
        BeanUtils.copyProperties(dto, author);
        repository.save(author);
        return new Response("Successfully Created - Author", HttpStatus.OK.value());
    }

    @SchemaMapping(typeName = "Mutation", value = "deleteByIdAuthor")
    public Response delete(@Argument Integer id) {
        repository.deleteById(id);
        return new Response("Successfully Deleted - Author", HttpStatus.OK.value());
    }
}
