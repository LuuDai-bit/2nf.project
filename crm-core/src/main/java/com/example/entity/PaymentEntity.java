package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class PaymentEntity extends BaseEntity{

    private static final long serialVersionUID = -935259071645362528L;

    @Column
    private Long consume_id;
    @Column
    private Integer amountPaid;
    @Column
    private Integer amountPayable;

    public Long getConsume_id() {
        return consume_id;
    }

    public void setConsume_id(Long consume_id) {
        this.consume_id = consume_id;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Integer amountPayable) {
        this.amountPayable = amountPayable;
    }
}
