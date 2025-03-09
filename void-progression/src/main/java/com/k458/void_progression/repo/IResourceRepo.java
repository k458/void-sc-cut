package com.k458.void_progression.repo;

import com.k458.void_progression.model.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResourceRepo extends JpaRepository<ResourceEntity, Long> {
}
