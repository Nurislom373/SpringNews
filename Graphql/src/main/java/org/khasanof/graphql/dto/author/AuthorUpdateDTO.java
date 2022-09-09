package org.khasanof.graphql.dto.author;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorUpdateDTO {
    private Integer id;
    private String name;
    private Integer age;
}
