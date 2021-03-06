package com.example.service.impl;

import com.example.controller.web.RestUploadController;
import com.example.converter.UserConverter;
import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;

import com.example.repository.IUserRepository;
import com.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UserDTO> searchUsers(UserDTO modelSearch, com.example.paging.Pageable pageable) {

        List<?> userEntities = new ArrayList<>();
        List<UserDTO> result = new ArrayList<>();

        userEntities = userRepository.findAll(modelSearch, pageable);
        for (Object item : userEntities) {
            UserEntity userEntity = new UserEntity();
            try {
                userEntity = (UserEntity) item;
            } catch (Exception e) {
                userEntity = (UserEntity) ((Object[]) item)[0];
            }
            UserDTO userDTO = userConverter.convertToDto(userEntity);

            result.add(userDTO);
        }

        return result;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for(UserEntity user : users){
            UserDTO userDTO = userConverter.convertToDto(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
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
    public void deleteUsers(List<Long> users) {
        for(Long userId : users){
                userRepository.delete(userId);
        }
    }

    @Override
    public int getTotalItems(UserDTO modelSearch) {
        int totalItem = 0;
        totalItem = userRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity user = userRepository.findOne(id);
        UserDTO userDTO = userConverter.convertToDto(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers(UserDTO userDTO) {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAllWithoutPageable(userDTO);
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for(UserEntity user : users){
            UserDTO dto = userConverter.convertToDto(user);
            userDTOS.add(dto);
        }
        return userDTOS;
    }
}
