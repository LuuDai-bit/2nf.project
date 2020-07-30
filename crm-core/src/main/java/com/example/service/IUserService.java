package com.example.service;

import com.example.dto.UserDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    List<UserDTO> searchUsers(UserDTO model, int pageNum, int maxPageItems);
    List<UserDTO> getAllUsers();
    List<UserDTO> getUsers(Pageable pageable);
    void saveUser(UserDTO user);
    boolean deleteUser(Long id);
    void deleteUsers(List<Long> users);
    int getTotalItems(UserDTO modelSearch);

}
