package com.k458.void_controller.service.progression;

import com.k458.void_controller.model.progression.RoomEntity;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RoomService {
    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-progression:8080/room").build();

    public Mono<ResponseEntity<RoomEntity>> get(Long id) {
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(RoomEntity.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<ResponseEntity<RoomEntity>> save(Long id, RoomEntity entity) {
        return webClient.post()
                .uri("/"+id)
                .bodyValue(entity)
                .retrieve()
                .bodyToMono(RoomEntity.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }
}