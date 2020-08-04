package com.example.repository.custom.impl;

import com.example.dto.CustomerDTO;
import com.example.paging.Pageable;
import com.example.repository.custom.CustomerRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ICustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<?> findAll(CustomerDTO CustomerDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT ue FROM CustomerEntity ue");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(CustomerDTO.getName())) {
            sql.append("AND LOWER(ue.name) LIKE LOWER('%" + CustomerDTO.getName() + "%')");
        }
        if (StringUtils.isNotBlank(CustomerDTO.getEmail())) {
            sql.append("AND LOWER(ue.email) LIKE LOWER('%" + CustomerDTO.getEmail() + "%')");
        }
        if (StringUtils.isNotBlank(CustomerDTO.getPhone())) {
            sql.append("AND LOWER(ue.phone) LIKE LOWER('%" + CustomerDTO.getPhone() + "%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(CustomerDTO CustomerDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM CustomerEntity AS ue");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(CustomerDTO.getName())) {
            sql.append("AND LOWER(ue.name) LIKE LOWER('%" + CustomerDTO.getName() + "%')");
        }
        if (StringUtils.isNotBlank(CustomerDTO.getEmail())) {
            sql.append("AND LOWER(ue.email) LIKE LOWER('%" + CustomerDTO.getEmail() + "%')");
        }
        if (StringUtils.isNotBlank(CustomerDTO.getPhone())) {
            sql.append("AND LOWER(ue.phone) LIKE LOWER('%" + CustomerDTO.getPhone() + "%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }
}
