package com.k458.void_gateway.controller;

import com.k458.void_gateway.model.UserEntity;
import com.k458.void_gateway.model.UserNamePassword;
import com.k458.void_gateway.service.GameControllerService;
import com.k458.void_gateway.service.SecurityService;
import com.k458.void_gateway.service.UserBottleneckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GatewayController {

    private final SecurityService securityService;
    private final UserBottleneckService userBottleneckService;
    private final GameControllerService gameControllerService;

    @GetMapping("/newGame")
    public Mono<ResponseEntity<String>> newGame(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return securityService.verifyToken(token)
                .flatMap(verifyTokenResponse -> {
                    if (verifyTokenResponse.getStatusCode() != HttpStatus.OK){
                        return Mono.just(ResponseEntity.badRequest().body("Token verification failed"));
                    }
                    Long id = verifyTokenResponse.getBody();
                    boolean allowed = userBottleneckService.tryAllowFor(id);
                    if (!allowed) {
                        return Mono.just(ResponseEntity.badRequest().body("Other request from the user is in process"));
                    }
                    return gameControllerService.newGame(id)
//                            .flatMap( newGameResponse -> {
//                                if (newGameResponse.getStatusCode() == HttpStatus.OK){
//                                    return Mono.just(ResponseEntity.ok(newGameResponse.getBody()));
//                                }
//                                return Mono.just(ResponseEntity.badRequest().body(newGameResponse.getBody()));
//                            })
                            .doFinally(signalType -> {
                                userBottleneckService.finishedFor(id);
                            })
                            .onErrorResume(error -> {
                                userBottleneckService.finishedFor(id);
                                return Mono.just(ResponseEntity.internalServerError().body("Unknown error"));
                            });
                })
                .onErrorResume(error -> {
                    return Mono.just(ResponseEntity.internalServerError().body("Failed to retrieve user id"));
                });
    }

    @GetMapping("/users")
    public Mono<ResponseEntity<List<UserEntity>>> users() {
        return securityService.getAllUsers();
    }

    @GetMapping("/items")
    public Mono<ResponseEntity<?>> getItems(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return securityService.verifyToken(token)
                .flatMap(verifyTokenResponse -> {
                    if (verifyTokenResponse.getStatusCode() != HttpStatus.OK){
                        return Mono.just(ResponseEntity.badRequest().body("Token verification failed"));
                    }
                    Long id = verifyTokenResponse.getBody();
                    boolean allowed = userBottleneckService.tryAllowFor(id);
                    if (!allowed) {
                        return Mono.just(ResponseEntity.badRequest().body("Other request from the user is in process"));
                    }
                    return gameControllerService.getItemsDTO(id)
                            .flatMap(items -> {
                                if (items.getStatusCode() == HttpStatus.OK){
                                    return Mono.just(ResponseEntity.ok(items));
                                }
                                return Mono.just(ResponseEntity.badRequest().body("Failed to retrieve items"));
                            })
                            .doFinally(signalType -> {
                                userBottleneckService.finishedFor(id);
                            })
                            .onErrorResume(error -> {
                                userBottleneckService.finishedFor(id);
                                return Mono.just(ResponseEntity.internalServerError().body("Unknown error"));
                            });
                })
                .onErrorResume(error -> {
                    return Mono.just(ResponseEntity.internalServerError().body("Failed to retrieve user id"));
                });
    }

    @PostMapping("/createUser")
    public Mono<ResponseEntity<String>> createUser(@RequestBody UserNamePassword unp) {
        System.out.println(unp.getName()+" "+unp.getPassword());
        return securityService.createUser(unp);
    }
}
