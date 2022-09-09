package org.khasanof.graphql.dto.blog;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogCreateDTO {
    private String title;
    private String description;
    private Integer authorId;
}
