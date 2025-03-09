package com.k458.void_creatures.model.items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long localId;
    @Column
    private String name;
    @Column
    private Integer durLeft;
    @Column
    private Integer level;
    @Column
    private String flags;
}
