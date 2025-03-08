package com.k458.void_resources.repo;

import com.k458.void_resources.model.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResourceRepo extends JpaRepository<ResourceEntity, Long> {
}
