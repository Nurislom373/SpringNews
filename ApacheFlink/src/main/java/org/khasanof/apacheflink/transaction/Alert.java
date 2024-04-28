package org.khasanof.apacheflink.transaction;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Nurislom
 * @see org.khasanof.apacheflink.transaction
 * @since 4/28/2024 9:04 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    private String id;
    private LocalDateTime timestamp;
    private String message;
    private String severity;
    private String source;
}
