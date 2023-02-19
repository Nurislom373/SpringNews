package org.khasanof.thymeleaf.models;

import lombok.*;

import java.util.Calendar;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/19/2023
 * <br/>
 * Time: 1:11 PM
 * <br/>
 * Package: org.khasanof.thymeleaf.models
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private Calendar date;
    private Customer customer;

}
