package com.sfeiropensource.schoolapp.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(properties = "spring.security.enabled=false")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerConfigurationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testSwagger() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/swagger-ui/index.html", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assert response.getBody() != null;
        assertTrue(response.getBody().contains("Swagger UI"), "Body must contain Swagger UI title");
    }
}