package com.sfeiropensource.schoolapp.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAuditTest {

    @InjectMocks
    private UserAudit userAudit;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCurrentAuditor() {
        Optional<String> expectedResult = Optional.of("auditor");

        assertEquals(expectedResult, this.userAudit.getCurrentAuditor());
    }
}