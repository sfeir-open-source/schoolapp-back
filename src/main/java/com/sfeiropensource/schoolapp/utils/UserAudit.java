package com.sfeiropensource.schoolapp.utils;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAudit implements AuditorAware<String> {
    /**
     * Return the current auditor from the possible provided token.
     *
     * @return Optional<String> - Possible the current email of the auditor
     */
    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of("auditor");
    }
}