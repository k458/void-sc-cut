package com.k458.void_controller.service.creatures;

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
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-items:8080/items").build();

    public Mono<ResponseEntity<ItemsDto>> getAll(Long id) {
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(ItemsDto.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<ResponseEntity<ItemEntity>> save(Long id, ItemEntity entity) {
        return webClient.post()
                .uri("/"+id)
                .bodyValue(entity)
                .retrieve()
                .bodyToMono(ItemEntity.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()));
    }

    public Mono<ResponseEntity<Void>> delete(Long id, Long localId) {
        return webClient.delete()
                .uri("/delete/"+id+"/"+localId)
                .retrieve()
                .bodyToMono(Void.class)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }
}
