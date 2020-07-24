package com.example.service;

import com.example.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    void saveUser(UserDTO user);

}
