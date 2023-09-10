package org.khasanof.websocket.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JsonRpcRequest extends JsonRpcDTO implements Serializable {

    @NotNull
    private Object params;

    public JsonRpcRequest(String id, @NotBlank String jsonrpc, String method, Object params) {
        super(jsonrpc, id, method);
        this.params = params;
    }

    public static JsonRpcRequest generateRequest(String method, Object params) {
        return new JsonRpcRequest(String.valueOf(System.currentTimeMillis()), "2.0", method, params);
    }
}
