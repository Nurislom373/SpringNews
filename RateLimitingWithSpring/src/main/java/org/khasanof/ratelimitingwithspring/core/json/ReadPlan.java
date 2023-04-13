package org.khasanof.ratelimitingwithspring.core.json;

import lombok.*;
import org.khasanof.ratelimitingwithspring.core.PricingPlan;
import org.khasanof.ratelimitingwithspring.domain.enums.RequestType;
import org.khasanof.ratelimitingwithspring.domain.enums.TimeType;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 11:33 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.core.json
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadPlan {
    private PricingPlan plan;
    private RequestType requestType;
    private Long requestCount;
    private TimeType timeType;
}
