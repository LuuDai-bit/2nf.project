package com.example.repository.custom.impl;

import com.example.dto.UserDTO;
import com.example.repository.custom.UserRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IUserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<?> findAll(UserDTO userDTO) {
        StringBuilder sql = new StringBuilder("FROM UserEntity AS u");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(userDTO.getName())) {
            sql.append("AND LOWER(u.name) LIKE LOWER('%"+ userDTO.getName()+"%') ");
        }
        if (StringUtils.isNotBlank(userDTO.getEmail())) {
            sql.append("AND LOWER(u.email) LIKE LOWER('%"+ userDTO.getEmail()+"%') ");
        }
        if (StringUtils.isNotBlank(userDTO.getPhone())) {
            sql.append("AND LOWER(u.phone) LIKE LOWER('%"+ userDTO.getPhone()+"%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        return query.getResultList();
    }
}
