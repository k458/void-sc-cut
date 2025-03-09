package com.k458.void_progression.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "resources")
public class ResourceEntity {
    @Id
    private Long id;
    @Column
    private Integer res1;
    @Column
    private Integer res2;
    @Column
    private Integer res3;
}
