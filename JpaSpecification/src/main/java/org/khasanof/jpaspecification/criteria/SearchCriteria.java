package org.khasanof.jpaspecification.criteria;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
