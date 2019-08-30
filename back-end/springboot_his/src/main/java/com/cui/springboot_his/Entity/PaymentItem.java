package com.cui.springboot_his.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "paymentitem")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "pay", procedureName = "Pay", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_paymentItem_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_others_id", type = Integer.class)
        }),
        @NamedStoredProcedureQuery(name = "refund", procedureName = "Refund", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_paymentItem_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_others_id", type = Integer.class)
        })
})
public class PaymentItem {
    private int id;
    private String content;
    private double fee;
    private String status;
    private Timestamp time;
    private int itemId;
    private Patient patient;
    private PaymentType paymentType;
    private Others others;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "item_id")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymenttype_id", insertable = false, updatable = false)
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "others_id", insertable = false, updatable = false)
    public Others getOthers() {
        return others;
    }

    public void setOthers(Others others) {
        this.others = others;
    }
}
