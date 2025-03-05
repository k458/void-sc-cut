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

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GatewayController {

    private final SecurityService securityService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> users() {
        ResponseEntity<List<UserEntity>> response = securityService.getAllUsers();
        if (response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/items")
    public ResponseEntity<String> getItems(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        ResponseEntity<Long> response = securityService.verifyToken(token);
        if (response.getStatusCode() == HttpStatus.OK){
            return new ResponseEntity<>(""+response.getBody(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserNamePassword unp) {
        System.out.println(unp.getName()+" "+unp.getPassword());
        return securityService.createUser(unp);
    }
}
