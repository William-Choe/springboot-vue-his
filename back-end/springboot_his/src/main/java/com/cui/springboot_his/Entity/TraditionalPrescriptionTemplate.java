package com.cui.springboot_his.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "traditionalprescriptiontemplate")
public class TraditionalPrescriptionTemplate {
    private int id;
    private String content;
    private Timestamp time;
    private String founder;

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
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "founder")
    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }
}
