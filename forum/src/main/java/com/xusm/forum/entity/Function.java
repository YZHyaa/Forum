package com.xusm.forum.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "function")
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer funId;

    private String funName;

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public Integer getFunState() {
        return funState;
    }

    public void setFunState(Integer funState) {
        this.funState = funState;
    }

    private Integer funState;
}
