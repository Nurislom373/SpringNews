package org.khasanof.websocket.server;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server
 * @since 9/10/2023 11:02 AM
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String form;
    private String text;

}
