package com.k458.void_gateway.service;

import com.k458.void_gateway.model.progression.ProgressionDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProgressionService {
    @LoadBalanced
    private final WebClient webClient = WebClient.builder().baseUrl("http://void-controller:8080/progression").build();

    public Mono<ResponseEntity<ProgressionDto>> get(Long id){
        return webClient.get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(ProgressionDto.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Void>> save(Long id, ProgressionDto dto){
        return webClient.post()
                .uri("/"+id)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Void.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.ok().build());
    }
}