package org.khasanof.openfeign;

import org.khasanof.openfeign.model.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign
 * @since 4/24/2024 11:05 AM
 */
@FeignClient(name = "todo")
public interface JsonPlaceholderTodoClient {

    @GetMapping("/todos")
    List<Todo> getTodos();
}
