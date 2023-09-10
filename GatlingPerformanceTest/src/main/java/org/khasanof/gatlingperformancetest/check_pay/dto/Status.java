package org.khasanof.gatlingperformancetest.check_pay.dto;

/**
 * Статус
 */
public enum Status {
    NEW,
    ACTIVE,
    DELETED,
    SUCCESS,
    ERROR,
    WAITING,
    CANCELLED,
    CONFIRM_ERROR,
    CONFIRM_CANCELLED,
    UNKNOWN,
    EXPIRED
}
