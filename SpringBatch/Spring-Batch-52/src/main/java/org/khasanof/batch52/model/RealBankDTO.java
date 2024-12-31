package org.khasanof.batch52.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.batch52
 * @since 12/17/2024 6:24 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RealBankDTO implements Serializable {

    private String mfo;

    private String name;

    private String address;

    private String city;
}
