package org.khasanof.openfeign.runner;

import org.khasanof.openfeign.JsonPlaceholderTodoClient;
import org.khasanof.openfeign.model.Todo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.runner
 * @since 4/24/2024 11:16 AM
 */
@Component
public class JsonPlaceholderTodoRunner implements ApplicationRunner {

    private final JsonPlaceholderTodoClient jsonPlaceholderTodoClient;

    public JsonPlaceholderTodoRunner(JsonPlaceholderTodoClient jsonPlaceholderTodoClient) {
        this.jsonPlaceholderTodoClient = jsonPlaceholderTodoClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Todo> todos = jsonPlaceholderTodoClient.getTodos();
        System.out.println("todos.get(0) = " + todos.get(0));
        System.out.println("todos.size() = " + todos.size());
    }
}
