package org.khasanof.mockito.argumentCaptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Nurislom
 * @see org.khasanof.mockito.argumentCaptor
 * @since 11/16/2023 8:35 AM
 */
@ExtendWith(MockitoExtension.class)
public class ArgumentCaptorTest {

    @Mock
    DeliveryService deliveryService;

    @InjectMocks
    EmailService emailService;

    @Captor
    ArgumentCaptor<Email> argumentCaptor;

    @Test
    void simpleTest() {

        String to = "info@baeldung.com";
        String subject = "Using ArgumentCaptor";
        String body = "Hey, let'use ArgumentCaptor";

        emailService.send(to, subject, body, true);

        Mockito.verify(deliveryService).deliver(argumentCaptor.capture());
        Email value = argumentCaptor.getValue();
        System.out.println("value = " + value);
        assertThat(value.getFormat()).isEqualTo(Format.HTML);

    }

}
