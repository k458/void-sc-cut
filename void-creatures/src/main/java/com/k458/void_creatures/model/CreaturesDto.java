package com.k458.void_creatures.model;

import com.k458.void_creatures.model.characters.CharacterEntity;
import com.k458.void_creatures.model.enemies.EnemyEntity;
import com.k458.void_creatures.model.items.ItemEntity;
import lombok.Data;

import java.util.List;

@Data
public class CreaturesDto {
    private List<CharacterEntity> characterEntities;
    private List<EnemyEntity> enemyEntities;
    private List<ItemEntity> itemEntities;
}
