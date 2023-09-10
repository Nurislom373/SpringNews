package org.khasanof.websocket.client.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonRpcDTO implements Serializable {
    @NotBlank
    private String jsonrpc;
    private String id;
    private String method;
}
