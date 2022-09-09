package org.khasanof.graphql.dto.author;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorCreateDTO {
    private Integer id;
    private String name;
    private Integer age;
}
