package com.k458.void_resources.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserResourcesEntity {

    @Id
    private Long id;
    @Column
    private int voidEssence;
}
