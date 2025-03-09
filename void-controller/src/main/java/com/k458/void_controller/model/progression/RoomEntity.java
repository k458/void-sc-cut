package com.k458.void_controller.model.progression;

import lombok.Data;

@Data
public class RoomEntity {
    private Long id;
    private Boolean isActive;
    private String name;
    private Integer sizeX;
    private Integer sizeY;
    private Integer round;
}
