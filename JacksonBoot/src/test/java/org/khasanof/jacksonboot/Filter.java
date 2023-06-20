package org.khasanof.jacksonboot;

import lombok.*;

import java.time.Instant;

/**
 * Author: Nurislom
 * <br/>
 * Date: 19.06.2023
 * <br/>
 * Time: 15:56
 * <br/>
 * Package: org.khasanof.jacksonboot
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    private String name;
    private Instant date;
}
