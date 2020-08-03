package com.example.repository.custom.impl;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.repository.custom.RoleRepositoryCustom;
import com.example.repository.custom.UserRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IRoleRepositoryImpl implements RoleRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<?> findAll(RoleDTO roleDTO) {
        StringBuilder sql = new StringBuilder("SELECT ue FROM RoleEntity ue");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(roleDTO.getName())) {
            sql.append("AND LOWER(ue.name) LIKE LOWER('%" + roleDTO.getName() + "%')");
        }
        if (StringUtils.isNotBlank(roleDTO.getCode())) {
            sql.append("AND LOWER(ue.code) LIKE LOWER('%" + roleDTO.getCode() + "%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult((roleDTO.getPage()-1)*roleDTO.getMaxPageItems());
        query.setMaxResults(roleDTO.getMaxPageItems());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(RoleDTO roleDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM RoleEntity AS ue");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(roleDTO.getName())) {
            sql.append("AND LOWER(ue.name) LIKE LOWER('%" + roleDTO.getName() + "%')");
        }
        if (StringUtils.isNotBlank(roleDTO.getCode())) {
            sql.append("AND LOWER(ue.code) LIKE LOWER('%" + roleDTO.getCode() + "%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }

}
