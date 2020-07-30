package com.example.repository;

import com.example.entity.UserEntity;
import com.example.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    Page<UserEntity> findAll(Pageable pageable);


}
