package com.k458.void_controller.model;

import com.k458.void_controller.config.Session;
import com.k458.void_controller.model.characters.CharacterEntity;
import com.k458.void_controller.model.enemies.EnemyEntity;
import com.k458.void_controller.model.items.ItemEntity;
import com.k458.void_controller.model.progression.DungeonEntity;
import com.k458.void_controller.model.progression.HubEntity;
import com.k458.void_controller.model.progression.ResourceEntity;
import com.k458.void_controller.model.progression.RoomEntity;
import com.k458.void_controller.service.creatures.CharacterService;
import com.k458.void_controller.service.creatures.EnemyService;
import com.k458.void_controller.service.creatures.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

public class PlayerSession {
    @Getter
    private final Long userId;
    @Getter
    private LocalDateTime expireTime;


    @Getter
    @Setter
    private HubEntity hubEntity;
    @Getter
    @Setter
    private DungeonEntity dungeonEntity;
    @Getter
    @Setter
    private RoomEntity roomEntity;
    @Getter
    @Setter
    private ResourceEntity resourceEntity;

    private TreeMap<Long, ItemEntity> items;
    private TreeMap<Long, CharacterEntity> characters;
    private TreeMap<Long, EnemyEntity> enemies;

    public PlayerSession(Long id){
        this.userId = id;
        refreshExpireTime();
    }

    private void refreshExpireTime(){
        expireTime = LocalDateTime.now().plus(Session.duration);
    }

    public List<CharacterEntity> getCharactersList(){
        return new ArrayList<>(characters.values());
    }

    public List<EnemyEntity> getEnemiesList(){
        return new ArrayList<>(enemies.values());
    }

    public List<ItemEntity> getItemsList(){
        return new ArrayList<>(items.values());
    }

    public CharacterEntity getCharacterEntity(Long localId){
        return characters.get(localId);
    }
    public EnemyEntity getEnemyEntity(Long localId){
        return enemies.get(localId);
    }
    public ItemEntity getItemEntity(Long localId){
        return items.get(localId);
    }

    public void removeCharacterEntity(Long localId){
        characters.remove(localId);
    }
    public void removeEnemyEntity(Long localId){
        enemies.remove(localId);
    }
    public void removeItemEntity(Long localId){
        items.remove(localId);
    }

    public void setCharacters(List<CharacterEntity> list){
        characters = new TreeMap<Long, CharacterEntity>();
        for (CharacterEntity v : list){
            characters.put(v.getLocalId(), v);
        }
    }
    public void setEnemies(List<EnemyEntity> list){
        enemies = new TreeMap<Long, EnemyEntity>();
        for (EnemyEntity v : list){
            enemies.put(v.getLocalId(), v);
        }
    }
    public void setItems(List<ItemEntity> list){
        items = new TreeMap<Long, ItemEntity>();
        for (ItemEntity i : list){
            items.put(i.getLocalId(), i);
        }
    }
}
