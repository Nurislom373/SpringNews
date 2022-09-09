package org.khasanof.graphql.utils;

import org.khasanof.graphql.entity.Author;
import org.khasanof.graphql.entity.Blog;
import org.khasanof.graphql.repository.AuthorRepository;
import org.khasanof.graphql.repository.BlogRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Init implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /*AuthorRepository authorRepository = applicationContext.getBean(AuthorRepository.class);
        BlogRepository blogRepository = applicationContext.getBean(BlogRepository.class);

        List<Author> authors = CSVParser.parseObjectList("src/main/resources/data/MOCK_DATA_AUTHOR.csv", Author.class);

        authorRepository.saveAll(authors);

        List<Blog> blogs = CSVParser.parseObjectList("src/main/resources/data/MOCK_DATA_BLOG.csv", Blog.class);

        for (int i = 0; i < blogs.size(); i++) {
            Blog blog = blogs.get(i);
            blog.setAuthor(authorRepository.findById(authors.get(i).getId()).orElseThrow());
            blogRepository.save(blog);
        }*/
        // only need to run once!
    }
}
