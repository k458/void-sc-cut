package com.k458.void_gateway.controller;

import com.k458.void_gateway.model.TokenTime;
import com.k458.void_gateway.model.UserEntity;
import com.k458.void_gateway.model.UserNamePassword;
import com.k458.void_gateway.service.SecurityService;
import com.k458.void_gateway.service.UserBottleneckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GatewayController {

    private final SecurityService securityService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> users() {
        return securityService.getAllUsers().block();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenTime> login(@RequestBody UserNamePassword unp) {
        return securityService.login(unp).block();
    }

    @GetMapping("/recreateToken/{token}")
    public ResponseEntity<TokenTime> recreateToken(@PathVariable("token") String token) {
        return securityService.recreateToken(token).block();
    }

    @PostMapping("/createUser")
    public ResponseEntity<TokenTime> createUser(@RequestBody UserNamePassword unp) {
        return securityService.createUser(unp).block();
    }
}
