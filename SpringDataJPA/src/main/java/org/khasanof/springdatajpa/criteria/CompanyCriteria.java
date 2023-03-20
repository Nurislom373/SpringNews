package org.khasanof.springdatajpa.criteria;

import lombok.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 5:52 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.criteria
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCriteria {
    private String key;
    private String operation;
    private String value;
}
