package com.rate.limite.application;

import com.rate.limite.application.ports.IRateLimiter;
import com.rate.limite.domain.CustomerRequestInfo;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class RateLimiterImpl implements IRateLimiter {

    private final Map<String, CustomerRequestInfo> clients = new ConcurrentHashMap<>();
    private final static int MAX_REQUESTS = 5;
    private final Duration WINDOW = Duration.ofSeconds(30);

    @Override
    public boolean isPermit(String clientId) {
        CustomerRequestInfo info = clients.computeIfAbsent(clientId, k -> new CustomerRequestInfo());

        Instant now = Instant.now();

        if (Duration.between(info.getWindowStart(), now).compareTo(WINDOW) > 0) {
            info.reset();
            return true;
        }

        if (info.getRequestCount() < MAX_REQUESTS) {
            info.increment();
            return true;
        }

        return false;
    }
}
