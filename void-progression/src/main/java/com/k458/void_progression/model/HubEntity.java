package com.k458.void_progression.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "hubs")
public class HubEntity {
    @Id
    private Long id;
    @Column
    private Boolean isActive;
    @Column
    private String name;
    @Column
    private Integer building1;
    @Column
    private Integer building2;
    @Column
    private Integer building3;
}
