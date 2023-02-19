package org.khasanof.thymeleaf.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/19/2023
 * <br/>
 * Time: 1:12 PM
 * <br/>
 * Package: org.khasanof.thymeleaf.models
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Boolean inStock;
    private List<Comment> comments;
}
