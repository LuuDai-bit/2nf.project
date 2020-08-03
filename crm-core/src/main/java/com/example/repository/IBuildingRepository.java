package com.example.repository;

import com.example.entity.BuildingEntity;
import com.example.entity.RoleEntity;
import com.example.repository.custom.BuildingRepositoryCustom;
import com.example.repository.custom.RoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
}
