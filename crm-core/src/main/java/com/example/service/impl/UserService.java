package com.example.service.impl;

import com.example.converter.UserConverter;
import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.repository.IUserRepository;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        List<UserEntity> newsEntities = userRepository.findAll();
        for(UserEntity item: newsEntities){
//            UserDTO userDTO = new UserDTO();
//            userDTO.setName(item.getName());
//            userDTO.setAvatar(item.getAvatar());
//            userDTO.setEmail(item.getEmail());
//            userDTO.setPhone(item.getPhone());
            UserDTO userDTO = userConverter.convertToDto(item);

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getUsers(Pageable pageable) {
        List<UserDTO> userDTOs = new ArrayList<>();
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        for(UserEntity item: userPage.getContent()){
            UserDTO userDTO = userConverter.convertToDto(item);

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void saveUser(UserDTO userDto){
        if(userDto.getId() !=null && userRepository.findOne(userDto.getId()) != null){
            UserEntity userEntity = userRepository.findOne(userDto.getId());
            userEntity.setName(userDto.getName());
            userEntity.setPhone(userDto.getPhone());
            userEntity.setEmail(userDto.getEmail());
            userRepository.save(userEntity);
        }
        else {
            UserEntity userEntity = userConverter.convertToEntity(userDto);
            userRepository.save(userEntity);
        }

    }

    @Override
    public boolean deleteUser(Long id){
        UserEntity userEntity = userRepository.findOne(id);
        if(userEntity!=null){
            userRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteUsers(List<Long> users) {
        for(Long userId : users){
            UserEntity userEntity = userRepository.findOne(userId);
            if(userEntity!=null){
                userRepository.delete(userId);
            }
        }
    }
}
