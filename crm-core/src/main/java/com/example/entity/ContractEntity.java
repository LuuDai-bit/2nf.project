package com.example.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "constract")
public class ContractEntity extends BaseEntity{

    private static final long serialVersionUID = 7348082054256962949L;
    @Column(nullable = false)
    private Long building_id;
    @Column(nullable = false)
    private Long customer_id;
    @Column(nullable = false)
    private Long user_id;
    @Column
    private boolean isDelete;
    @Column
    private String note;

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
