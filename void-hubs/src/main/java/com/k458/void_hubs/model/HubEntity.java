package com.k458.void_hubs.model;

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
    private String name;
    @Column
    private String flags;
}
