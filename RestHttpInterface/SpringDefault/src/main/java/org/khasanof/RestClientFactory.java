package org.khasanof;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.khasanof.GlobalConstants.BASE_URL;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/4/2024 10:00 PM
 */
@Component
public class RestClientFactory implements HttpClientFactory {

    @Override
    public SpringDefaultRestClient factory() {
        RestClient restClient = getRestClient();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter)
                .build();

        return httpServiceProxyFactory.createClient(SpringDefaultRestClient.class);
    }

    private RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
