package org.khasanof.openfeign.model;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.model
 * @since 4/24/2024 10:51 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;

}
