package com.cui.springboot_his.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "invoice")
public class Invoice {
    private int id;
    private double fee;
    private String type;
    private Timestamp time;
    private PaymentItem paymentItem;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fee")
    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentitem_id", insertable = false, updatable = false)
    public PaymentItem getPaymentItem() {
        return paymentItem;
    }

    public void setPaymentItem(PaymentItem paymentItem) {
        this.paymentItem = paymentItem;
    }
}
