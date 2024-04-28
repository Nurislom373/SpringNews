package org.khasanof.apacheflink.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink.transaction
 * @since 4/28/2024 7:48 AM
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String id;
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String status;
    private String type;
    private String sourceAccountId;
    private String destinationAccountId;
}
