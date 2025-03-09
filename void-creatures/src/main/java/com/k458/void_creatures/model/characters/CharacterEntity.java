package com.k458.void_creatures.model.characters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "characters")
public class CharacterEntity {
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
}
