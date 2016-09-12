package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(TestableService.class)
public class TestableServiceTest {

    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private TestableService service;

    @Test
    public void testFindOne() throws Exception {
        server.expect(requestTo("/endpoint"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string("some body"))
                .andRespond(withSuccess("responseBody", MediaType.TEXT_PLAIN));

        service.callEndpoint("some body");

        server.verify();

    }
}
