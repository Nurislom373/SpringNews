package org.khasanof.mockito.argumentCaptor;

/**
 * @author Nurislom
 * @see org.khasanof.mockito.argumentCaptor
 * @since 11/16/2023 8:35 AM
 */
public class EmailService {

    private DeliveryService deliveryService;

    public void send(String to, String subject, String body, boolean html) {
        Format format = Format.TEXT_ONLY;
        if (html) {
            format = Format.HTML;
        }
        Email email = new Email(to, subject, body);
        email.setFormat(format);
        deliveryService.deliver(email);
    }

}
