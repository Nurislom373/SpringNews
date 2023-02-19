package org.khasanof.thymeleaf.models;

import lombok.*;

import java.math.BigDecimal;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/19/2023
 * <br/>
 * Time: 1:13 PM
 * <br/>
 * Package: org.khasanof.thymeleaf.models
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    private Product product;
    private Integer amount;
    private BigDecimal purchasePrice;
}
