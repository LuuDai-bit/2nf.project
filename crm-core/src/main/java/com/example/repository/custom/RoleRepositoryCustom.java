package com.example.repository.custom;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.RoleEntity;
import com.example.paging.Pageable;

import java.util.List;

public interface RoleRepositoryCustom {
    List<?> findAll(RoleDTO roleDTO, Pageable pageable);
    Long getTotalItems(RoleDTO roleDTO);
}
