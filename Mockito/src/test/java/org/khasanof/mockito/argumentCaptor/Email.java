package org.khasanof.mockito.argumentCaptor;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.mockito.argumentCaptor
 * @since 11/16/2023 8:36 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    private String to;
    private String subject;
    private String body;
    private Format format;

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
