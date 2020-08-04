package com.example.repository.custom.impl;

import com.example.dto.UnitPriceDTO;
import com.example.paging.Pageable;
import com.example.repository.custom.UnitPriceRepositoryCustom;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IUnitPriceRepositoryImpl implements UnitPriceRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<?> findAll(UnitPriceDTO unitPriceDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT ue FROM UnitPriceEntity ue");
        sql.append(" WHERE 1=1 ");
        if (unitPriceDTO.getRoomPrice()!=null) {
            sql.append("AND ue.roomPrice =:" + unitPriceDTO.getRoomPrice() + "");
        }
        if (unitPriceDTO.getElectricityPrice()!=null) {
            sql.append("AND ue.electricityPrice =:" + unitPriceDTO.getElectricityPrice() + "");
        }
        if (unitPriceDTO.getWaterPrice()!=null) {
            sql.append("AND ue.waterPrice =:" + unitPriceDTO.getWaterPrice() + "");
        }

        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(UnitPriceDTO unitPriceDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM UnitPriceEntity AS ue");
        sql.append(" WHERE 1=1 ");
        if (unitPriceDTO.getRoomPrice()!=null) {
            sql.append("AND ue.roomPrice =:" + unitPriceDTO.getRoomPrice() + "");
        }
        if (unitPriceDTO.getElectricityPrice()!=null) {
            sql.append("AND ue.electricityPrice =:" + unitPriceDTO.getElectricityPrice() + "");
        }
        if (unitPriceDTO.getWaterPrice()!=null) {
            sql.append("AND ue.waterPrice =:" + unitPriceDTO.getWaterPrice() + "");
        }

        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }
    
}
