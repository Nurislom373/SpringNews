package org.khasanof.openfeign.model;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.model
 * @since 4/24/2024 9:55 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Integer id;
    private Integer userId;
    private String title;
    private String body;

}
