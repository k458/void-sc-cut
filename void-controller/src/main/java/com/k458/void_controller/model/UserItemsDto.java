package com.k458.void_controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class UserItemsDto {
    private List<ItemEntity> items;
}
