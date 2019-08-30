package com.cui.springboot_his.Entity;

import javax.persistence.*;

@Entity
@Table(name = "medicineprescription")
public class MedicinePrescription {
    private int id;
    private String content;
    private Integer number;
    private String state;
    private TreatmentPlan treatmentPlan;

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
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentplan_id", insertable = false, updatable = false)
    public TreatmentPlan getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(TreatmentPlan treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }
}
