package com.example.service;

import com.example.dto.UserDTO;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    List<UserDTO> getUsers(Pageable pageable);
    void saveUser(UserDTO user);
    boolean deleteUser(Long id);
    void deleteUsers(List<Long> users);

}
