package org.khasanof.openfeign;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign
 * @since 4/24/2024 10:43 AM
 */
@Configuration
@Import(FeignClientsConfiguration.class)
public class CreatingFeignClientManuallyConfiguration {

    /**
     *
     * @param client
     * @param encoder
     * @param decoder
     * @param contract
     * @return
     */
    @Bean
    public JsonPlaceholderCommentClient placeholderCommentClient(Client client, Encoder encoder, Decoder decoder, Contract contract) {
        return Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("test", "test"))
                .target(JsonPlaceholderCommentClient.class, "https://jsonplaceholder.typicode.com/");
    }
}
