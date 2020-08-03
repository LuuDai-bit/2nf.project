package com.example.repository.custom;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.RoleEntity;

import java.util.List;

public interface RoleRepositoryCustom {
    List<?> findAll(RoleDTO roleDTO);
    Long getTotalItems(RoleDTO roleDTO);
}
