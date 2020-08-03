package com.example.dto;

public class PaymentDTO extends BaseDTO{
    private Long consume_id;
    private Integer amountPaid;
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
