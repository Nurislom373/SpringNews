package org.khasanof.ratelimitingwithspring.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 9:46 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.domain
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pricing_api", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class PricingApiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auth_user_id")
    private AuthUserEntity authUser;

    @ManyToOne
    @JoinColumn(name = "api_id")
    private ApiEntity api;

    @ManyToOne
    @JoinColumn(name = "limited_id")
    private LimitedEntity limited;

    @ManyToOne
    @JoinColumn(name = "customized_id")
    private CustomizedEntity customized;

    @Basic(optional = false)
    private boolean customize;

    @Column(name = "pricing_count", nullable = false)
    private Integer pricingCount;
}
