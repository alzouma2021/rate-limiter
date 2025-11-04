package com.rate.limite.infrastructure.gateway.inbound.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/rate-limiter")
public class RateLimiterController {

    @GetMapping
    public ResponseEntity<String> rateLimiter(){
        return new ResponseEntity<>("Vous êtes autoriser à executer cette requête", HttpStatus.CREATED);
    }
}
