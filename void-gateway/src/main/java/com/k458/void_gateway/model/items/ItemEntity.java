package com.k458.void_gateway.model.items;

import lombok.Data;

@Data
public class ItemEntity {
    private Long id;
    private Long userId;
    private Long localId;
    private String name;
    private Integer durLeft;
    private Integer level;
    private String flags;
}
