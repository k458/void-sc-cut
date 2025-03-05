package com.k458.void_gateway.service;

import com.k458.void_gateway.model.UserEntity;
import com.k458.void_gateway.model.UserNamePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String secUrl = "http://localhost:8081";

    public ResponseEntity<List<UserEntity>> getAllUsers(){
        String url = "/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<UserEntity>> response = restTemplate.exchange(secUrl+url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<UserEntity>>() {});
            return response;
        } catch (Exception e) {
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<Long> verifyToken(String token){
        String url = "/verifyToken";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(token, headers);
        try {
            ResponseEntity<Long> response = restTemplate.exchange(secUrl+url, HttpMethod.POST, requestEntity, Long.class);
            return response;
        } catch (Exception e) {
        }
        return new ResponseEntity<>(1L, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<String> createUser(UserNamePassword unp){
        String url = "/createUser";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserNamePassword> requestEntity = new HttpEntity<>(unp, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(secUrl+url, HttpMethod.POST, requestEntity, String.class);
            return response;
        } catch (Exception e) {
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
