package com.k458.void_gateway.model.characters;

import lombok.Data;

@Data
public class CharacterEntity {
    private Long id;
    private Long userId;
    private Long localId;
    private String name;
    private String type;
    private Integer level;
}
