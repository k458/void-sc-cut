package com.k458.void_gateway.service;

import com.k458.void_gateway.model.ItemsDTO;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameControllerService {

    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-controller:8080").build();

    public Mono<ResponseEntity<Void>> test(){
        return webClient.get()
                .uri("/test")
                .retrieve()
                .bodyToMono(Void.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.ok().build());
    }

    public Mono<ResponseEntity<String>> newGame(Long id){
        return webClient.post()
                .uri("/newGame/"+id)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.ok().build());
    }

    public Mono<ResponseEntity<ItemsDTO>> getItemsDTO(Long id){
        return webClient.get()
                .uri("/items/"+id)
                .retrieve()
                .bodyToMono(ItemsDTO.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
