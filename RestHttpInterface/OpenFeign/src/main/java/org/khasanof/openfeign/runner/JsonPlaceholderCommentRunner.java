package org.khasanof.openfeign.runner;

import org.khasanof.openfeign.JsonPlaceholderCommentClient;
import org.khasanof.openfeign.model.Comment;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.runner
 * @since 4/24/2024 10:55 AM
 */
@Order(2)
@Component
public class JsonPlaceholderCommentRunner implements ApplicationRunner {

    private final JsonPlaceholderCommentClient placeholderCommentClient;

    public JsonPlaceholderCommentRunner(JsonPlaceholderCommentClient placeholderCommentClient) {
        this.placeholderCommentClient = placeholderCommentClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        List<Comment> comments = placeholderCommentClient.getComments();
//        System.out.println("comments.get(0) = " + comments.get(0));
//        System.out.println("comments.size() = " + comments.size());
    }
}
