package com.cui.springboot_his.Entity;

import javax.persistence.*;

@Entity
@Table(name = "traditionalprescription")
public class TraditionalPrescription {
    private int id;
    private String content;
    private Integer number;
    private String state;
    private int treatmentPlanId;

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

    @Basic
    @Column(name = "treatmentPlan_id")
    public int getTreatmentPlanId() {
        return treatmentPlanId;
    }

    public void setTreatmentPlanId(int treatmentPlanId) {
        this.treatmentPlanId = treatmentPlanId;
    }
}
