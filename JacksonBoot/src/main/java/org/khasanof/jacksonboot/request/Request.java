package org.khasanof.jacksonboot.request;

import lombok.*;

import java.util.Date;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/18/2023
 * <br/>
 * Time: 10:26 PM
 * <br/>
 * Package: org.khasanof.jacksonboot.request
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String carName;
    private Date datePurchased;
}
