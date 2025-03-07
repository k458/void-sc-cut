package com.k458.void_controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GameController {

    @PostMapping("/newGame/{id}")
    public Mono<ResponseEntity<String>> newGame(@PathVariable("id") Long id){
        return Mono.just(ResponseEntity.ok("New Game"));
    }
}
