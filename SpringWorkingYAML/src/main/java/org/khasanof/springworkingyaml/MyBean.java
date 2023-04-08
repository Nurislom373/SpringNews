package org.khasanof.springworkingyaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class MyBean {

    @Value("${my.secret}")
    private String value;

    @Value("${my.number}")
    private Integer number;

    @Value("${my.bignumber}")
    private Long bigNumber;

    @Value("${my.numberOne.less.than.ten}")
    private Long numberOneLessThanTen;

}
