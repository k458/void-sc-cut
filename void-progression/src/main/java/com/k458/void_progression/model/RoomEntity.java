package com.k458.void_progression.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class RoomEntity {
    @Id
    private Long id;
    @Column
    private Boolean isActive;
    @Column
    private String name;
    @Column
    private Integer sizeX;
    @Column
    private Integer sizeY;
    @Column
    private Integer round;
}
