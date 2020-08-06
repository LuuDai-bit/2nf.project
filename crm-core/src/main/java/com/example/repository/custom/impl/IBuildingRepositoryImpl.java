package com.example.repository.custom.impl;

import com.example.dto.BuildingDTO;
import com.example.paging.Pageable;
import com.example.repository.custom.BuildingRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IBuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<?> findAll(BuildingDTO buildingDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT ue FROM BuildingEntity ue");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(buildingDTO.getCode())) {
            sql.append("AND LOWER(ue.code) LIKE LOWER('%" + buildingDTO.getCode() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getDistrict())) {
            sql.append("AND LOWER(ue.district) LIKE LOWER('%" + buildingDTO.getDistrict() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getStreet())) {
            sql.append("AND LOWER(ue.street) LIKE LOWER('%" + buildingDTO.getStreet() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getWard())) {
            sql.append("AND LOWER(ue.ward) LIKE LOWER('%" + buildingDTO.getWard() + "%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public List<?> findAllWithoutPageable(BuildingDTO buildingDTO) {
        StringBuilder sql = new StringBuilder("SELECT ue FROM BuildingEntity ue");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(buildingDTO.getCode())) {
            sql.append("AND LOWER(ue.code) LIKE LOWER('%" + buildingDTO.getCode() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getDistrict())) {
            sql.append("AND LOWER(ue.district) LIKE LOWER('%" + buildingDTO.getDistrict() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getStreet())) {
            sql.append("AND LOWER(ue.street) LIKE LOWER('%" + buildingDTO.getStreet() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getWard())) {
            sql.append("AND LOWER(ue.ward) LIKE LOWER('%" + buildingDTO.getWard() + "%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(BuildingDTO buildingDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM BuildingEntity AS ue");
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(buildingDTO.getCode())) {
            sql.append("AND LOWER(ue.code) LIKE LOWER('%" + buildingDTO.getCode() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getDistrict())) {
            sql.append("AND LOWER(ue.district) LIKE LOWER('%" + buildingDTO.getDistrict() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getStreet())) {
            sql.append("AND LOWER(ue.street) LIKE LOWER('%" + buildingDTO.getStreet() + "%')");
        }
        if (StringUtils.isNotBlank(buildingDTO.getWard())) {
            sql.append("AND LOWER(ue.ward) LIKE LOWER('%" + buildingDTO.getWard() + "%')");
        }

        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }
}
