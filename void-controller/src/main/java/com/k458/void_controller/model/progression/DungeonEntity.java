package com.k458.void_controller.model.progression;

import lombok.Data;

@Data
public class DungeonEntity {
    private Long id;
    private Boolean isActive;
    private String name;
    private Integer level;
    private Integer roomsCompleted;
    private Integer roomsTotal;
}
