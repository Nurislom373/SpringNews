package org.khasanof.websocket.server;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server
 * @since 9/18/2023 6:36 PM
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    private String username;
    private String password;
}
