package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "unit_price")
public class UnitPriceEntity extends BaseEntity{

    private static final long serialVersionUID = 6366374740057345663L;
    @Column
    private Integer electricityPrice;
    @Column
    private Integer RoomPrice;
    @Column
    private Integer waterPrice;
    @Column(nullable=false)
    private Long building_id;
    @Column
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
