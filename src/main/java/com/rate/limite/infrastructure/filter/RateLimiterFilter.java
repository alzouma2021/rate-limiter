package com.rate.limite.infrastructure.filter;

import com.rate.limite.application.ports.IRateLimiter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateLimiterFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterFilter.class);

    private final IRateLimiter rateLimiter;

    public RateLimiterFilter(IRateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String clientIp = request.getRemoteAddr();

        logger.info("#### address client info with clientIp: "+clientIp);

        if (!rateLimiter.isPermit(clientIp)) {
            response.setStatus(429);
            response.getWriter().write("Too Many Requests");
            throw new RuntimeException("Too Many Requests");
        }

        chain.doFilter(request, response);
    }
}
