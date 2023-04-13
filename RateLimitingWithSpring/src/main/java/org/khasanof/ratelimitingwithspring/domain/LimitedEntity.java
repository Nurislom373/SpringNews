package org.khasanof.ratelimitingwithspring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.khasanof.ratelimitingwithspring.domain.enums.PricingPlan;
import org.khasanof.ratelimitingwithspring.domain.enums.RequestType;
import org.khasanof.ratelimitingwithspring.domain.enums.TimeType;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 12:18 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.domain
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "limited", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class LimitedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_count", nullable = false)
    private Long requestCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "limit_type", nullable = false)
    private TimeType timeType;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private PricingPlan plan;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    private RequestType requestType;

    @ManyToOne
    @JoinColumn(name = "api_id",
            foreignKey = @ForeignKey(name = "api_id_fk"))
    private ApiEntity api;

}
