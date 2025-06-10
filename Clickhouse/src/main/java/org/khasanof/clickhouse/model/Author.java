package org.khasanof.clickhouse.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Nurislom
 * @see org.khasanof.clickhouse.model
 * @since 4/18/25
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private UUID id;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public Author(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
}
