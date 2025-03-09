package com.k458.void_controller.service.creatures;

import com.k458.void_controller.model.enemies.EnemiesDto;
import com.k458.void_controller.model.items.ItemEntity;
import com.k458.void_controller.model.items.ItemsDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ItemService {
    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-creatures:8080/items").build();

    public Mono<ResponseEntity<ItemsDto>> get(Long id) {
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(ItemsDto.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()));
    }

    public Mono<ResponseEntity<Void>> save(Long id, ItemsDto dto) {
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
