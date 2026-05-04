package org.khasanof.domain;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author Nurislom
 * @see org.khasanof.domain
 * @since 5/4/26
 */
@Getter
@Setter
@ToString
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @PrimaryKey
    private String id;
    private String name;
    private Integer age;
}
