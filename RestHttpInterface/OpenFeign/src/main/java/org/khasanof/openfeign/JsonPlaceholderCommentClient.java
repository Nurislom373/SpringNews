package org.khasanof.openfeign;

import org.khasanof.openfeign.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign
 * @since 4/24/2024 10:52 AM
 */
@FeignClient(name = "comment")
public interface JsonPlaceholderCommentClient {

    @GetMapping("/comments")
    List<Comment> getComments();
}
