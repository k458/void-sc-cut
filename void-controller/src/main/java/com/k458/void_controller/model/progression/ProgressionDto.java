package com.k458.void_controller.model.progression;

import lombok.Data;

@Data
public class ProgressionDto {
    private DungeonEntity dungeonEntity;
    private HubEntity hubEntity;
    private ResourceEntity resourceEntity;
    private RoomEntity roomEntity;
}
