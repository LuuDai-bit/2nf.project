package com.example.repository;

import com.example.entity.UserEntity;
import com.example.repository.custom.UserRepositoryCustom;
import org.jboss.logging.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    @Modifying
    @Query("delete from UserEntity u where u.id = ?1")
    void deleteUserById(Long id);

}
