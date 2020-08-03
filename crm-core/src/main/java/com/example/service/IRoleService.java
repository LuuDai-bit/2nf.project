package com.example.service;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface IRoleService {
    RoleDTO getOneRoleById(Long id);
    List<RoleDTO> searchRoles(RoleDTO modelSearch, Pageable pageable);
    int getTotalItems(RoleDTO modelSearch);
    void saveRole(RoleDTO role);
    void deleteRoles(List<Long> roles);
    List<RoleDTO> getAllRoles();
}
