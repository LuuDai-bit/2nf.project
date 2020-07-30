package com.example.service.impl;

import com.example.converter.UserConverter;
import com.example.dto.UserDTO;
import com.example.entity.UserEntity;

import com.example.repository.IUserRepository;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<UserDTO> searchUsers(UserDTO modelSearch) {

        List<?> userEntities = null;
        List<UserDTO> result = new ArrayList<>();

        userEntities = userRepository.findAll(modelSearch);
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
    public List<UserDTO> getAllUsers(){
        Pageable pageable =new PageRequest(0,5);

        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        for(UserEntity item: userEntities){
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

    @Override
    public int getTotalItems(UserDTO modelSearch) {
        int totalItem = 0;
        totalItem = userRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }
}
