package com.k458.void_controller.service;

import com.k458.void_controller.config.Session;
import com.k458.void_controller.model.PlayerSession;
import com.k458.void_controller.model.UserItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class PlayerSessionService {
    private ItemService itemService;
    private final ConcurrentMap<Long, PlayerSession> sessions = new ConcurrentHashMap<>();

    public PlayerSession getSession(Long userId){
        PlayerSession session = sessions.get(userId);
        if(session == null){
            session = new PlayerSession();
            session.setUserId(userId);
            session.setExpireTime(LocalDateTime.now().plus(Session.duration));
            ResponseEntity<UserItemsDto> responseEntity = itemService.getItems(userId).block();
            if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
                UserItemsDto itemsDto = responseEntity.getBody();
                if(itemsDto == null){
                    return null;
                }
                session.setItems(itemsDto.getItems());
            }
        }
        sessions.put(userId, session);
        return session;
    }
}
