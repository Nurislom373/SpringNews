package org.khasanof.authentication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDTO {
    private String username;
    private String password;
}
