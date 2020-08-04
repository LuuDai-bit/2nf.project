package com.example.repository.custom.impl;

import com.example.dto.PaymentDTO;
import com.example.dto.PaymentDTO;
import com.example.paging.Pageable;
import com.example.repository.custom.PaymentRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IPaymentRepositoryImpl implements PaymentRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<?> findAll(PaymentDTO paymentDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT ue FROM PaymentEntity ue");
        sql.append(" WHERE 1=1 ");
        if (paymentDTO.getAmountPaid()!=null) {
            sql.append("AND ue.amountPaid =:" + paymentDTO.getAmountPaid() + "");
        }
        if (paymentDTO.getAmountPayable()!=null) {
            sql.append("AND ue.amountPayable =:" + paymentDTO.getAmountPayable() + "");
        }

        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(PaymentDTO paymentDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM PaymentEntity AS ue");
        sql.append(" WHERE 1=1 ");
        if (paymentDTO.getAmountPaid()!=null) {
            sql.append("AND ue.amountPaid =:" + paymentDTO.getAmountPaid() + "");
        }
        if (paymentDTO.getAmountPayable()!=null) {
            sql.append("AND ue.amountPayable =:" + paymentDTO.getAmountPayable() + "");
        }

        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }
}
