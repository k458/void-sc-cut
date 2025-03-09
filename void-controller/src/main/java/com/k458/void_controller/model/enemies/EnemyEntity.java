package com.k458.void_controller.model.enemies;

import lombok.Data;

@Data
public class EnemyEntity {
    private Long id;
    private Long userId;
    private Long localId;
    private String name;
    private String type;
    private Integer level;
    private Integer hpLeft;
}
