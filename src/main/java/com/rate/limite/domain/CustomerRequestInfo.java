package com.rate.limite.domain;

import java.time.Instant;

public class CustomerRequestInfo {
    private int requestCount;
    private Instant windowStart;

    public CustomerRequestInfo() {
        this.requestCount = 0;
        this.windowStart = Instant.now();
    }

    public int getRequestCount() {
        return requestCount;
    }

    public Instant getWindowStart() {
        return windowStart;
    }

    public void increment() {
        requestCount++;
    }

    public void reset() {
        this.requestCount = 0;
        this.windowStart = Instant.now();
    }
}
