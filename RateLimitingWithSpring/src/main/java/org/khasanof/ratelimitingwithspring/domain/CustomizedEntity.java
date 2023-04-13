package org.khasanof.ratelimitingwithspring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.khasanof.ratelimitingwithspring.domain.enums.RequestType;
import org.khasanof.ratelimitingwithspring.domain.enums.TimeType;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 10:06 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.domain
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customized_entity", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class CustomizedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_count", nullable = false)
    private Long requestCount;

    @Enumerated
    @Column(name = "request_type")
    private RequestType requestType;

    @Enumerated
    @Column(name = "time_type")
    private TimeType timeType;
}
