package com.cui.springboot_his.Entity;

import javax.persistence.*;
import javax.print.Doc;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "registrationform")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "registration", procedureName = "Registration", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_sex", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_age", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_birth", type = Date.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_occupation", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_idNumber", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_department_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_registeredLevel_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_doctor_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_phone", type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "registration_mrid", procedureName = "Registration_mrid", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_medicalRecord_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_department_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_registeredLevel_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_doctor_id", type = Integer.class),
        }),
        @NamedStoredProcedureQuery(name = "back_registration", procedureName = "Back_Registration", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_registrationForm_id", type = Integer.class)
        })
})
public class RegistrationForm {
    private int id;
    private Timestamp time;
    private String status;
    private Department department;
    private RegisteredLevel registeredLevel;
    private Patient patient;
    private Doctor doctor;

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
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
