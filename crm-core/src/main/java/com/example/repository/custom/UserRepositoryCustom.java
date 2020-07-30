package com.example.repository.custom;

import com.example.dto.UserDTO;

import java.util.List;

public interface UserRepositoryCustom {
    List<?> findAll(UserDTO userDTO, int pageNum, int maxPageItems);
    Long getTotalItems(UserDTO customerDTO);

}
