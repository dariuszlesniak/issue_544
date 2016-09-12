package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestableService {
    private RestTemplate restTemplate;

    public TestableService(RestTemplateBuilder builder) {

        restTemplate = builder.rootUri("http://someserver").build();
    }

    public String callEndpoint(String body) {
        return restTemplate.postForObject("/endpoint", body, String.class);
    }
}
