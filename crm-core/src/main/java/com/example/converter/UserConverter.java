package com.example.converter;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.repository.IRoleRepository;
import com.example.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IRoleRepository roleRepository;

    public UserDTO convertToDto(UserEntity entity){
        UserDTO result = modelMapper.map(entity,UserDTO.class);

        return result;
    }

    public UserEntity convertToEntity(UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
//        result.setRole(roleRepository.findOne(dto.getRole().getId()));
        return result;
    }
}
