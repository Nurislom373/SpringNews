package org.khasanof.websocket.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

    private Boolean success;
    private String message;
    private T data;
    private Integer code;
    private String error;

    public ResponseDTO(T data) {
        this.data = data;
        this.success = true;
    }

    public ResponseDTO(Boolean success, String message, Integer code, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public ResponseDTO(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static ResponseDTO withSuccess(Boolean success) {
        var response = new ResponseDTO<>();
        response.success = success;
        response.message = "OK";
        return response;
    }

    public ResponseDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDTO(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public final static ResponseDTO<Object> SUCCESS = new ResponseDTO<>(true, "OK", null, null);
    public final static ResponseDTO<Object> FAIL = new ResponseDTO<>(false, "FAILED", null, null);
}
