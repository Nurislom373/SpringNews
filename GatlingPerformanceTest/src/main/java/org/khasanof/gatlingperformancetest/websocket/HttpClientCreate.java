package org.khasanof.gatlingperformancetest.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.khasanof.gatlingperformancetest.websocket.JsonRpcConstants.phoneNumber;
import static org.khasanof.gatlingperformancetest.websocket.JsonRpcConstants.randomPartner;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.websocket
 * @since 8/29/2023 5:41 PM
 */
public class HttpClientCreate {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final RestTemplate restTemplate = new RestTemplate();

    public static Optional<String> getToken(RegisterDTO dto) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost("https://auth.b1nk.uz/realms/hamyon-business/protocol/openid-connect/token");
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("grant_type", "password"));
            urlParameters.add(new BasicNameValuePair("client_id", "microservice"));
            urlParameters.add(new BasicNameValuePair("client_secret", "0zNy3sAOGowODP8hwgNqAm0raK8k0eIl"));
            urlParameters.add(new BasicNameValuePair("scope", "openid"));
            urlParameters.add(new BasicNameValuePair("username", dto.getInn()));
            urlParameters.add(new BasicNameValuePair("password", dto.getPassword()));
            request.setEntity(new UrlEncodedFormEntity(urlParameters));
            CloseableHttpResponse response = httpClient.execute(request);
            Map<String, String> readValue = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
            });
            if (response.getStatusLine().getStatusCode() == 200) {
                return Optional.of(readValue.get("access_token"));
            }
            return Optional.empty();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public static Optional<RegisterDTO> register() {
        String url = "https://api.b1nk.uz/api/register-partner";
        RegisterDTO registerDTO = new RegisterDTO(RandomStringUtils.random(9, false, true),
                phoneNumber(), "123", randomPartner());
        HttpEntity<RegisterDTO> entity = new HttpEntity<>(registerDTO);
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.of(registerDTO);
        } else {
            return Optional.empty();
        }
    }

}
