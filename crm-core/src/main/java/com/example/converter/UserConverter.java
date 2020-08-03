package com.example.converter;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repository.IRoleRepository;
import com.example.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private RoleConverter roleConverter;

    public UserDTO convertToDto(UserEntity entity){
        UserDTO result = modelMapper.map(entity,UserDTO.class);

        return result;
    }

    public UserEntity convertToEntity(UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }
}
