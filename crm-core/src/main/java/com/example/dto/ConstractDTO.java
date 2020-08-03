package com.example.dto;

import com.example.entity.BaseEntity;

public class ConstractDTO extends BaseEntity {
    private Long building_id;
    private Long customer_id;
    private Long user_id;
    private boolean isDelete;
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
