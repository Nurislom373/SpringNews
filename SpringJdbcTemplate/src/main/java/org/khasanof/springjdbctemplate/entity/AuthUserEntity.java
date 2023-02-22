package org.khasanof.springjdbctemplate.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/22/2023
 * <br/>
 * Time: 6:38 PM
 * <br/>
 * Package: org.khasanof.springjdbctemplate.entity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserEntity {
    private Integer id;
    private String username;
    private String password;
    private Date createdAt;
}
