package com.k458.void_controller.service;

import com.k458.void_controller.model.characters.CharacterEntity;
import com.k458.void_controller.model.enemies.EnemyEntity;
import com.k458.void_controller.model.items.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityMergerService {

    public CharacterEntity mergeCharacterEntity(CharacterEntity thatWillBeReturned, CharacterEntity thatKeepsChanges){
        thatWillBeReturned.setHpLeft(thatKeepsChanges.getHpLeft());
        thatWillBeReturned.setActionLeft(thatKeepsChanges.getActionLeft());
        thatWillBeReturned.setMoveLeft(thatKeepsChanges.getMoveLeft());
        thatWillBeReturned.setIsFielded(thatKeepsChanges.getIsFielded());
        thatWillBeReturned.setPosX(thatKeepsChanges.getPosX());
        thatWillBeReturned.setPosY(thatKeepsChanges.getPosY());
        return thatWillBeReturned;
    }

    public EnemyEntity mergeEnemyEntity(EnemyEntity thatWillBeReturned, EnemyEntity thatKeepsChanges) {
        thatWillBeReturned.setHpLeft(thatKeepsChanges.getHpLeft());
        thatWillBeReturned.setActionLeft(thatKeepsChanges.getActionLeft());
        thatWillBeReturned.setMoveLeft(thatKeepsChanges.getMoveLeft());
        thatWillBeReturned.setIsFielded(thatKeepsChanges.getIsFielded());
        thatWillBeReturned.setPosX(thatKeepsChanges.getPosX());
        thatWillBeReturned.setPosY(thatKeepsChanges.getPosY());
        return thatWillBeReturned;
    }

    public ItemEntity mergeItemEntity(ItemEntity thatWillBeReturned, ItemEntity thatKeepsChanges) {

        return thatWillBeReturned;
    }
}
