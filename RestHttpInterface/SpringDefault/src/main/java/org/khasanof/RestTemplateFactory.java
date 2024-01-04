package org.khasanof;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.khasanof.GlobalConstants.BASE_URL;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/4/2024 10:13 PM
 */
@Component
public class RestTemplateFactory implements HttpClientFactory {

    @Override
    public SpringDefaultRestClient factory() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(BASE_URL));

        RestTemplateAdapter restTemplateAdapter = RestTemplateAdapter.create(restTemplate);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restTemplateAdapter)
                .build();

        return httpServiceProxyFactory.createClient(SpringDefaultRestClient.class);
    }

}
