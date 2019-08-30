package com.cui.springboot_his.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "treatmentplan")
@NamedStoredProcedureQuery(name = "prescription", procedureName = "Prescribe", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_medicalRecord_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_prescribeName", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "drug_str", type = String.class)
})
public class TreatmentPlan {
    private int id;
    private Timestamp time;
    private String detail;
    private MedicalRecord medicalRecord;

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
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalrecord_id", insertable = false, updatable = false)
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
