package org.khasanof.openfeign.model;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.model
 * @since 4/24/2024 11:04 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private Integer id;
    private Integer userId;
    private String title;
    private Boolean completed;

}
