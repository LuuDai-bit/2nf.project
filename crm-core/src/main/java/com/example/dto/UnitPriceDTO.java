package com.example.dto;

import java.util.Date;

public class UnitPriceDTO extends BaseDTO{
    private Integer electricityPrice;
    private Integer RoomPrice;
    private Integer waterPrice;
    private Long building_id;
    private Date effectiveDate;

    public Integer getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(Integer electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public Integer getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        RoomPrice = roomPrice;
    }

    public Integer getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(Integer waterPrice) {
        this.waterPrice = waterPrice;
    }

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
