package com.sfeiropensource.schoolapp.configuration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityConfigurationTest {

    @Nested
    @TestPropertySource(properties = "spring.security.enabled=true")
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class EnabledSecurityConfigurationTest {

        @Autowired
        private TestRestTemplate testRestTemplate;

        @Test
        void testSecurity() {
            ResponseEntity<String> response = testRestTemplate.getForEntity("/schools", String.class);

            // Should return no authorized.
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

    }

    @Nested
    @ActiveProfiles("test")
    @TestPropertySource(properties = "spring.security.enabled=true")
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class DisabledSecurityConfigurationTest {

        @Autowired
        private TestRestTemplate testRestTemplate;

        @Test
        void testSecurity() {
            ResponseEntity<String> response = testRestTemplate.getForEntity("/schools", String.class);
//
//            // Should return no authorized.
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

    }

}