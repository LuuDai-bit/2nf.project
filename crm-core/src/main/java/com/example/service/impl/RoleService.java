package com.example.service.impl;

import com.example.dto.RoleDTO;
import com.example.entity.RoleEntity;
import com.example.repository.IRoleRepository;
import com.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
        List<RoleEntity> roleEntities = roleRepository.findAll();
        for(RoleEntity item: roleEntities){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setName(item.getName());
            roleDTO.setCode(item.getCode());

            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }
}
