package com.cui.springboot_his.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "medicalrecord")
@NamedStoredProcedureQuery(name = "diagnose", procedureName = "Diagnose", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_registrationForm_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_zs", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_xbs", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_xbzlqk", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_jws", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_gms", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_tgjc", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_cbzd", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_jcjy", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_zysx", type = String.class),
})
public class MedicalRecord {
    private int id;
    private Timestamp time;
    private String zs;
    private String xbs;
    private String xbzlqk;
    private String jws;
    private String gms;
    private String tgjc;
    private String cbzd;
    private String jcjy;
    private String zysx;
    private Patient patient;

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
    @Column(name = "zs")
    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    @Basic
    @Column(name = "xbs")
    public String getXbs() {
        return xbs;
    }

    public void setXbs(String xbs) {
        this.xbs = xbs;
    }

    @Basic
    @Column(name = "xbzlqk")
    public String getXbzlqk() {
        return xbzlqk;
    }

    public void setXbzlqk(String xbzlqk) {
        this.xbzlqk = xbzlqk;
    }

    @Basic
    @Column(name = "jws")
    public String getJws() {
        return jws;
    }

    public void setJws(String jws) {
        this.jws = jws;
    }

    @Basic
    @Column(name = "gms")
    public String getGms() {
        return gms;
    }

    public void setGms(String gms) {
        this.gms = gms;
    }

    @Basic
    @Column(name = "tgjc")
    public String getTgjc() {
        return tgjc;
    }

    public void setTgjc(String tgjc) {
        this.tgjc = tgjc;
    }

    @Basic
    @Column(name = "cbzd")
    public String getCbzd() {
        return cbzd;
    }

    public void setCbzd(String cbzd) {
        this.cbzd = cbzd;
    }

    @Basic
    @Column(name = "jcjy")
    public String getJcjy() {
        return jcjy;
    }

    public void setJcjy(String jcjy) {
        this.jcjy = jcjy;
    }

    @Basic
    @Column(name = "zysx")
    public String getZysx() {
        return zysx;
    }

    public void setZysx(String zysx) {
        this.zysx = zysx;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
