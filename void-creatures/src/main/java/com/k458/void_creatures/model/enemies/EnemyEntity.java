package com.k458.void_creatures.model.enemies;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "enemies")
public class EnemyEntity {
    @Id
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long localId;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private Integer level;
    @Column
    private Integer hpLeft;
}
