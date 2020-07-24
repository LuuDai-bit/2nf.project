package com.example.service.impl;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.repository.IRoleRepository;
import com.example.repository.IUserRepository;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        List<UserEntity> newsEntities = userRepository.findAll();
        for(UserEntity item: newsEntities){
            UserDTO userDTO = new UserDTO();
            userDTO.setName(item.getName());
            userDTO.setAvatar(item.getAvatar());
            userDTO.setEmail(item.getEmail());
            userDTO.setPhone(item.getPhone());

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void saveUser(UserDTO userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAvatar(userDto.getAvatar());
        userEntity.setPhone(userDto.getPhone());
        userRepository.save(userEntity);
    }
}
