package org.khasanof.gatlingperformancetest.websocket;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.websocket
 * @since 8/29/2023 4:38 PM
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String inn;
    private String phoneNumber;
    private String password;
    private PartnerType partnerType;

    public enum PartnerType {
        BUSINESS, GAS_STATION
    }

}
