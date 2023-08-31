package org.khasanof.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JsonRpcResponse extends JsonRpcDTO implements Serializable {

    private Object result;

    private JsonRpcError error;

    public JsonRpcResponse(String id, String jsonRpc, Object result, JsonRpcError error) {
        this.setId(id);
        this.setJsonrpc(jsonRpc);
        this.setResult(result);
        this.setError(error);
    }

    public JsonRpcResponse(String id, Object result, String method) {
        this.setId(id);
        this.setJsonrpc("2.0");
        this.setResult(result);
        this.setError(null);
        this.setMethod(method);
    }


    public JsonRpcResponse(String id, String jsonRpc, Object result, JsonRpcError error, String method) {
        this.setId(id);
        this.setJsonrpc(jsonRpc);
        this.setResult(result);
        this.setError(error);
        this.setMethod(method);
    }


}
