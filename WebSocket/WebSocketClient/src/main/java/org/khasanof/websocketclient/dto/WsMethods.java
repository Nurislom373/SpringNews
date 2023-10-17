package org.khasanof.websocketclient.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Nurislom
 * @see uz.javlon.v220.service.websocket
 * @since 9/16/2023 3:38 PM
 */
@Getter
@RequiredArgsConstructor
public enum WsMethods {

    OPEN_CONNECTION,
    METER_VALUES,
    STATUS_CHANGED_OBJECTS,
    SEARCH_NEARBY_CHARGE_BOXES,
    START_TRANSACTION,
    START_TRANSACTION_SIMULATE,
    STOP_TRANSACTION,
    STOP_TRANSACTION_SIMULATE
}
