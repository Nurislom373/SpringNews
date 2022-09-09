package org.khasanof.graphql.controller;

import org.khasanof.graphql.dto.blog.BlogCreateDTO;
import org.khasanof.graphql.dto.blog.BlogUpdateDTO;
import org.khasanof.graphql.entity.Blog;
import org.khasanof.graphql.response.Response;
import org.khasanof.graphql.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService service;

    @SchemaMapping(typeName = "Query", value = "allBlogs")
    public List<Blog> findAll() {
        return service.list();
    }

    @QueryMapping
    public Blog findByIdBlog(@Argument Integer id) {
        return service.findById(id);
    }

    @MutationMapping
    public Response saveBlog(@Argument BlogCreateDTO dto) {
        service.save(dto);
        return new Response("Successfully Created - Blog", HttpStatus.CREATED.value());
    }

    @MutationMapping
    public Response updateBlog(@Argument BlogUpdateDTO dto) {
        service.update(dto);
        return new Response("Successfully Updated - Blog", HttpStatus.OK.value());
    }

    @MutationMapping
    public Response deleteByIdBlog(@Argument Integer id) {
        service.delete(id);
        return new Response("Successfully Deleted - Blog", HttpStatus.OK.value());
    }
}
