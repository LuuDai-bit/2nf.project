package com.example.service.impl;

import com.example.converter.RoleConverter;
import com.example.dto.RoleDTO;
import com.example.entity.RoleEntity;
import com.example.paging.Pageable;
import com.example.repository.IRoleRepository;
import com.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public RoleDTO getOneRoleById(Long id) {
        RoleEntity roleEntity = roleRepository.findOne(id);
        RoleDTO roleDTO = roleConverter.convertToDto(roleEntity);
        return roleDTO;
    }

    @Override
    public List<RoleDTO> searchRoles(RoleDTO modelSearch, Pageable pageable) {

        List<?> roleEntities = new ArrayList<>();
        List<RoleDTO> result = new ArrayList<>();

        roleEntities = roleRepository.findAll(modelSearch, pageable);
        for (Object item : roleEntities) {
            RoleEntity roleEntity = new RoleEntity();
            try {
                roleEntity = (RoleEntity) item;
            } catch (Exception e) {
                roleEntity = (RoleEntity) ((Object[]) item)[0];
            }
            RoleDTO roleDTO = roleConverter.convertToDto(roleEntity);

            result.add(roleDTO);
        }

        return result;
    }

    @Override
    public int getTotalItems(RoleDTO modelSearch) {
        int totalItem = 0;
        totalItem = roleRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }

    @Override
    public void saveRole(RoleDTO roleDto) {
        if(roleDto.getId() !=null && roleRepository.findOne(roleDto.getId()) != null){
            RoleEntity roleEntity = roleRepository.findOne(roleDto.getId());
            roleEntity.setName(roleDto.getName());
            roleEntity.setCode(roleDto.getCode());
            roleRepository.save(roleEntity);
        }
        else {
            RoleEntity roleEntity = roleConverter.convertToEntity(roleDto);
            roleRepository.save(roleEntity);
        }
    }

    @Override
    public void deleteRoles(List<Long> roles) {
        for(Long roleId : roles){
            roleRepository.delete(roleId);
        }
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<RoleEntity> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        for(RoleEntity role : roles){
            RoleDTO roleDTO = roleConverter.convertToDto(role);
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }
}
