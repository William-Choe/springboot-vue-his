package com.cui.springboot_his.Entity;

import javax.persistence.*;

@Entity
@Table(name = "disposalitem")
@NamedStoredProcedureQuery(name = "add_disposal", procedureName = "Add_Disposal", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_medicalRecord_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_nDrug_id", type = Integer.class),
})
public class DisposalItem {
    private int id;
    private String content;

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
}
