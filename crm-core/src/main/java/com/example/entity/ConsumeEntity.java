package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="consume")
public class ConsumeEntity extends BaseEntity{
    private static final long serialVersionUID = 6781869158365226079L;
    @Column(nullable=false)
    private Long contract_id;
    @Column
    private Integer debit;
    @Column
    private Integer powerNumber;
    @Column
    private Integer waterNumber;
    @Column
    private String note;

    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    public Integer getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(Integer powerNumber) {
        this.powerNumber = powerNumber;
    }

    public Integer getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(Integer waterNumber) {
        this.waterNumber = waterNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
