package org.khasanof.graphql.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    private String message;
    private Integer code;
}
