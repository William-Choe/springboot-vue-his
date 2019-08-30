package com.cui.springboot_his.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "schedule")
public class Schedule {
    private int id;
    private Date time;
    private Doctor doctor;
    private Department department;
    private RegisteredLevel registeredLevel;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registeredlevel_id", insertable = false, updatable = false)
    public RegisteredLevel getRegisteredLevel() {
        return registeredLevel;
    }

    public void setRegisteredLevel(RegisteredLevel registeredLevel) {
        this.registeredLevel = registeredLevel;
    }
}
