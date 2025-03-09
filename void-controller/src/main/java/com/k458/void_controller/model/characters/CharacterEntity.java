package com.k458.void_controller.model.characters;

import lombok.Data;

@Data
public class CharacterEntity {
    private Long id;
    private Long userId;
    private Long localId;
    private String name;
    private String type;
    private Integer posX;
    private Integer posY;
    private Integer hpLeft;
    private Integer actionLeft;
    private Integer moveLeft;
    private Integer speed;
    private Integer speedRolled;
    private Boolean acted;
    private Integer level;
    private Boolean isFielded;
}
