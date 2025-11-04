package com.rate.limite.application.ports;

public interface IRateLimiter {
    boolean isPermit(String clientId);
}
