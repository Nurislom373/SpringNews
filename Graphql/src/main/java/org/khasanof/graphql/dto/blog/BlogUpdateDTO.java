package org.khasanof.graphql.dto.blog;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogUpdateDTO {
    private Integer id;
    private String title;
    private String description;
}
