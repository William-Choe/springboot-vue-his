package com.cui.springboot_his.Entity;

import javax.persistence.*;

@Entity
@Table(name = "drug")
@NamedStoredProcedureQuery(name = "dispensedrug", procedureName = "DispenseDrug", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "n_medicalRecord_id", type = Integer.class)
})
public class Drug {
    private int id;
    private String name;
    private String unit;
    private double price;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
