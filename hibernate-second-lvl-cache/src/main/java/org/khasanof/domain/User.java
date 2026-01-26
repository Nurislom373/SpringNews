package org.khasanof.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 11/4/25
 */
@Getter
@Setter
@Entity
@ToString
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lvl_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
