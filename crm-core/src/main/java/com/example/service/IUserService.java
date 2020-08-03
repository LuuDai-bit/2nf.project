package com.example.service;

import com.example.dto.UserDTO;

import com.example.paging.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    List<UserDTO> searchUsers(UserDTO model, Pageable pageable);

    void saveUser(UserDTO user);
    void deleteUsers(List<Long> users);
    int getTotalItems(UserDTO modelSearch);
    UserDTO getUserById(Long id);
}
