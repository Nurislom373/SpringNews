package org.khasanof.websocket.client.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonRpcError implements Serializable {

    @NotNull
    private Integer code;

    @NotNull
    private String message;

    private String data;
}
