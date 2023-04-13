package org.khasanof.ratelimitingwithspring.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/11/2023
 * <br/>
 * Time: 1:27 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.test
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auth_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class AuthUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "api_key")
    private String apiKey;
}
