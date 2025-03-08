package com.k458.void_controller.service;

import com.k458.void_controller.model.ItemEntity;
import com.k458.void_controller.model.UserItemsDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ItemService {
    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-items:8080").build();

    public Mono<ResponseEntity<UserItemsDto>> getItems(Long id) {
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(UserItemsDto.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<ResponseEntity<ItemEntity>> saveItem(ItemEntity itemEntity) {
        return webClient.post()
                .uri("/saveItem")
                .bodyValue(itemEntity)
                .retrieve()
                .bodyToMono(ItemEntity.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()));
    }

    public Mono<ResponseEntity<Void>> deleteItem(Long id, Long localId) {
        return webClient.delete()
                .uri("/deleteItem/"+id+"/"+localId)
                .retrieve()
                .bodyToMono(Void.class)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()));
    }
}
