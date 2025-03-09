package com.k458.void_controller.service;

import com.k458.void_controller.model.CreaturesDto;
import com.k458.void_controller.model.PlayerSession;
import com.k458.void_controller.model.progression.ProgressionDto;
import com.k458.void_controller.service.creatures.CreaturesService;
import com.k458.void_controller.service.progression.ProgressionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class PlayerSessionService {

    private final ProgressionService progressionService;
    private final CreaturesService creaturesService;

    private final ConcurrentMap<Long, PlayerSession> sessions = new ConcurrentHashMap<>();

    public PlayerSession getSession(Long userId){
        PlayerSession session = sessions.get(userId);
        if(session == null){
            session = new PlayerSession(userId);
            ResponseEntity<ProgressionDto> resProgDto = progressionService.get(userId).block();
            if (resProgDto == null || resProgDto.getStatusCode() != HttpStatus.OK){
                System.out.println("ERROR Failed to retrieve progression for: "+userId);
                return null;
            }
            ProgressionDto pDto = resProgDto.getBody();
            session.setRoomEntity(pDto.getRoomEntity());
            session.setHubEntity(pDto.getHubEntity());
            session.setDungeonEntity(pDto.getDungeonEntity());
            session.setResourceEntity(pDto.getResourceEntity());

            ResponseEntity<CreaturesDto> resCreDto = creaturesService.getAll(userId).block();
            if (resCreDto == null || resCreDto.getStatusCode() != HttpStatus.OK){
                System.out.println("ERROR Failed to retrieve creatures for: "+userId);
                return null;
            }
            CreaturesDto cDto = resCreDto.getBody();
            session.setItems(cDto.getItemEntities());
            session.setEnemies(cDto.getEnemyEntities());
            session.setCharacters(cDto.getCharacterEntities());

            if (session.getHubEntity().getIsActive()){
                // new game
            }
            sessions.put(userId, session);
        }
        return session;
    }
}
