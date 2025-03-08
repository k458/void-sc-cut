package com.k458.void_items.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long itemId;
    @Column
    private String name;
    @Column
    private Integer durLeft;
    @Column
    private Integer level;
    @Column
    private String flags;
}
