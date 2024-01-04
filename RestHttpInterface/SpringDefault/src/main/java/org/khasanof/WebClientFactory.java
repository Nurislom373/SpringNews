package org.khasanof;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.khasanof.GlobalConstants.BASE_URL;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/4/2024 10:10 PM
 */
@Component
public class WebClientFactory implements HttpClientFactory {

    @Override
    public SpringDefaultRestClient factory() {
        WebClient webClient = webClientBuilder();

        WebClientAdapter webClientAdapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(webClientAdapter)
                .build();

        return httpServiceProxyFactory.createClient(SpringDefaultRestClient.class);
    }

    private WebClient webClientBuilder() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
