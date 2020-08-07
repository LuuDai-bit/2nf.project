package com.example.repository.custom;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.paging.Pageable;


import java.util.List;

public interface UserRepositoryCustom {
    List<?> findAll(UserDTO userDTO, Pageable pageable);
    List<?> findAllWithoutPageable(UserDTO userDTO);
    Long getTotalItems(UserDTO userDTO);
}
