package org.khasanof.gatlingperformancetest.check_pay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WsData<T> implements Serializable {
    private Boolean reply = false;
    private String service;
    private String destination;
    private String username;
    private String recipientUsername; // TODO: 4/17/2023 Need move to other
    private Boolean sendUsername = false; // TODO: 4/17/2023 Need move to other
    private T data;

    private WsData(Boolean bool, T data) {
        this.reply = bool;
        this.data = data;
    }

    public static WsData<?> replyDATA(Object data) {
        return new WsData<>(true, data);
    }
}
