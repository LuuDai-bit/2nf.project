package com.example.dto;

public class ConsumeDTO extends BaseDTO{
    private Long contract_id;
    private Integer debit;
    private Integer powerNumber;
    private Integer waterNumber;
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
