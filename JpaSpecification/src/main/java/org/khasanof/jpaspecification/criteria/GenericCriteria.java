package org.khasanof.jpaspecification.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class GenericCriteria {
    private Integer size;
    private Integer page;
    private String key;
    private Sort.Direction direction;

    public GenericCriteria(Integer size, Integer page) {
        this.size = size;
        this.page = page;
    }

    public GenericCriteria(Integer size, Integer page, String key, Sort.Direction direction) {
        this.size = size;
        this.page = page;
        this.key = key;
        this.direction = direction;
    }
}
