package com.k458.void_gateway.controller;

import com.k458.void_gateway.model.UserEntity;
import com.k458.void_gateway.model.UserNamePassword;
import com.k458.void_gateway.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GatewayController {

    private final SecurityService securityService;

    @GetMapping("/users")
    public Mono<ResponseEntity<List<UserEntity>>> users() {
        return securityService.getAllUsers();
    }

    @GetMapping("/items")
    public Mono<ResponseEntity<String>> getItems(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return securityService.verifyToken(token)
                .flatMap(value -> {
                    return securityService.createUser(new UserNamePassword("User#"+value.getBody(), "pisword"))  // assuming anotherService.performAction() returns a Mono<ResponseEntity<String>>
                            .map(response -> {
                                return ResponseEntity.ok("Success: " + response.getBody());
                            });
                })
                .onErrorResume(error -> {
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token verification failed"));
                });
    }

    @PostMapping("/createUser")
    public Mono<ResponseEntity<String>> createUser(@RequestBody UserNamePassword unp) {
        System.out.println(unp.getName()+" "+unp.getPassword());
        return securityService.createUser(unp);
    }
}
