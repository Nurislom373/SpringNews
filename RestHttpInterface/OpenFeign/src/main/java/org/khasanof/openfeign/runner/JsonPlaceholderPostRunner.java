package org.khasanof.openfeign.runner;

import org.khasanof.openfeign.JsonPlaceholderPostClient;
import org.khasanof.openfeign.model.Post;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.runner
 * @since 4/24/2024 9:58 AM
 */
@Order(1)
@Component
public class JsonPlaceholderPostRunner implements ApplicationRunner {

    private final JsonPlaceholderPostClient placeholderPostClient;

    public JsonPlaceholderPostRunner(JsonPlaceholderPostClient placeholderPostClient) {
        this.placeholderPostClient = placeholderPostClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Post> posts = placeholderPostClient.getPosts();
        System.out.println("posts.size() = " + posts.size());
        System.out.println("posts.get(0) = " + posts.get(0));
    }
}
