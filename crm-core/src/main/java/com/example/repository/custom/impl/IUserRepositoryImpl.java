package com.example.repository.custom.impl;

import com.example.dto.UserDTO;
import com.example.paging.Pageable;
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
        query.setFirstResult(userDTO.getPage()-1);
        query.setMaxResults(userDTO.getMaxPageItems());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(UserDTO userDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM UserEntity AS ue");
//        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
//            sql.append(" JOIN ce.users u ");
//        }
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(userDTO.getName())) {
            sql.append("AND LOWER(ue.name) LIKE LOWER('%" + userDTO.getName() + "%')");
        }
        if (StringUtils.isNotBlank(userDTO.getPhone())) {
            sql.append("AND LOWER(ue.phone) LIKE LOWER('%" + userDTO.getPhone() + "%')");
        }
        if (StringUtils.isNotBlank(userDTO.getEmail())) {
            sql.append("AND LOWER(ue.email) LIKE LOWER('%" + userDTO.getEmail() + "%')");
        }
        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }
}
