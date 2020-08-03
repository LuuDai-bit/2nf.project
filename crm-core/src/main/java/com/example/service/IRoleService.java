package com.example.service;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;

import java.util.List;

public interface IRoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getOneRoleById(Long id);
    List<RoleDTO> searchRoles(RoleDTO modelSearch);
    int getTotalItems(RoleDTO modelSearch);
    void saveRole(RoleDTO role);
    void deleteRoles(List<Long> roles);
}
