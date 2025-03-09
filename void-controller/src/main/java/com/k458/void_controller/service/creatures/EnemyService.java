package com.k458.void_controller.service.creatures;

import com.k458.void_controller.model.characters.CharactersDto;
import com.k458.void_controller.model.enemies.EnemiesDto;
import com.k458.void_controller.model.enemies.EnemyEntity;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EnemyService {
    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-creatures:8080/enemies").build();

    public Mono<ResponseEntity<EnemiesDto>> get(Long id) {
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(EnemiesDto.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()));
    }

    public Mono<ResponseEntity<Void>> save(Long id, EnemiesDto dto) {
        return webClient.post()
                .uri("/"+id)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Void.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()));
    }
}
