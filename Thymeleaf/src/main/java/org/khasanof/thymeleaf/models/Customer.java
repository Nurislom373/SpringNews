package org.khasanof.thymeleaf.models;

import lombok.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/19/2023
 * <br/>
 * Time: 1:10 PM
 * <br/>
 * Package: org.khasanof.thymeleaf.models
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Integer id;
    private String name;
    private Date customerSince;
}
