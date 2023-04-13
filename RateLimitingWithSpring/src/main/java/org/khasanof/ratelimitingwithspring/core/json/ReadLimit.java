package org.khasanof.ratelimitingwithspring.core.json;

import lombok.*;
import org.khasanof.ratelimitingwithspring.domain.enums.Method;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 11:32 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.core.json
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadLimit {
    private String url;
    private Method method;
    private List<ReadPlan> plans;
}
