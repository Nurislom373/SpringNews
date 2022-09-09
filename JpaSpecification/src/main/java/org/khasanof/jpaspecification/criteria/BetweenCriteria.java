package org.khasanof.jpaspecification.criteria;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BetweenCriteria {
    private String key;
    private int from;
    private int to;
}
