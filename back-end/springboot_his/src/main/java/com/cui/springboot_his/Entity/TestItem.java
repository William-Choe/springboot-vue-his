package com.cui.springboot_his.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "testitem")
@NamedStoredProcedureQuery(name = "add_test", procedureName = "Add_Test", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_medicalRecord_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_nDrug_id", type = Integer.class),
})
public class TestItem {
    private int id;
    private Timestamp time;
    private String content;
    private String analysis;
    private String result;

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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "analysis")
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
