package org.khasanof.thymeleaf.models;

import lombok.*;

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
public class Comment {

    private Integer id;
    private String text;
}
