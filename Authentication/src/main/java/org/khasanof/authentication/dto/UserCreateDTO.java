package org.khasanof.authentication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCreateDTO {
    private String username;
    private String email;
    private String password;
}
