package com.k458.void_items.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class UserItemsDto {
    private Long userId;
    private List<ItemEntity> items;
}
