package org.khasanof.websocketclient.dto;

import lombok.*;

/**
 * @author Nurislom
 * @see uz.javlon.v220.service.websocket.dto
 * @since 9/17/2023 11:13 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WsDTO {

    private WsMethods method;

    private String id;
}
