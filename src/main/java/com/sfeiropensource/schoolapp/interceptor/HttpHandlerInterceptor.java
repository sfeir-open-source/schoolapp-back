package com.sfeiropensource.schoolapp.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;

@Slf4j
@NoArgsConstructor
public class HttpHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {

        // Save current timestamp.
        MDC.put("startRequest", String.valueOf(Instant.now().toEpochMilli()));

        // Log information about the request.
        log.info("Request received [{}] {}", request.getMethod(), request.getRequestURI());
        if (!ObjectUtils.isEmpty(request.getQueryString())) {
            log.info("Query params : {}", request.getQueryString());
        }

        // Return true to not perturb current process.
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        long requestDuration = Instant.now().toEpochMilli() - Long.parseLong(MDC.get("startRequest"));
        log.info("Response {} - [{}] {} in {} ms", response.getStatus(), request.getMethod(), request.getRequestURI(), requestDuration);
    }
}
