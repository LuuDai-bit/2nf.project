package com.example.service;

import com.example.dto.UserDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    List<UserDTO> searchUsers(UserDTO model);
    List<UserDTO> getAllUsers();
    void saveUser(UserDTO user);
    void deleteUsers(List<Long> users);
    int getTotalItems(UserDTO modelSearch);
    UserDTO getUserById(Long id);
}
