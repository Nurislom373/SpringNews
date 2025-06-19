package org.khasanof.hazelcast.model;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.hazelcast.model
 * @since 6/10/25
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String password;
    private String email;
}
