package com.k458.void_gateway.service;

import com.k458.void_gateway.model.UserEntity;
import com.k458.void_gateway.model.UserNamePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-security:8080").build();

    public Mono<ResponseEntity<List<UserEntity>>> getAllUsers() {
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserEntity>>() {})
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Long>> verifyToken(String token) {
        return webClient.get()
                .uri("/verifyToken/"+token)
                .retrieve()
                .bodyToMono(Long.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<String>> createUser(UserNamePassword unp) {
        return webClient.post()
                .uri("/createUser")
                .bodyValue(unp)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
