package com.example.repository.custom;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserRepositoryCustom {
    List<?> findAll(UserDTO userDTO);
}
