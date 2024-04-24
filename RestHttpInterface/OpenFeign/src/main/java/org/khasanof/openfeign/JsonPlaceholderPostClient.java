package org.khasanof.openfeign;

import org.khasanof.openfeign.config.SimpleFeignConfiguration;
import org.khasanof.openfeign.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign
 * @since 4/24/2024 9:56 AM
 */
@FeignClient(name = "post", url = "https://jsonplaceholder.typicode.com", configuration = SimpleFeignConfiguration.class)
public interface JsonPlaceholderPostClient {

    @GetMapping("/posts")
    List<Post> getPosts();
}
