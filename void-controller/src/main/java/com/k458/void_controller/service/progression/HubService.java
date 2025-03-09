package com.k458.void_controller.service.progression;

import com.k458.void_controller.model.progression.HubEntity;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HubService {
    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-progression:8080/hub").build();

    public Mono<ResponseEntity<HubEntity>> get(Long id) {
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(HubEntity.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<ResponseEntity<HubEntity>> save(Long id, HubEntity entity) {
        return webClient.post()
                .uri("/"+id)
                .bodyValue(entity)
                .retrieve()
                .bodyToMono(HubEntity.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }
}