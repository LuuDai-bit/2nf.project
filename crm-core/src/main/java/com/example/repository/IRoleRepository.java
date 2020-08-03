package com.example.repository;

import com.example.entity.RoleEntity;
import com.example.repository.custom.RoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long>, RoleRepositoryCustom {

}
