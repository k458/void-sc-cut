package com.k458.void_resources.repo;

import com.k458.void_resources.model.UserResourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IResourcesRepo extends JpaRepository<UserResourcesEntity, Long> {
}
