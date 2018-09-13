/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.techmania.glgservice.domain;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author a0284827
 */
public class GLGLptOpnSeq implements Serializable {
    
    private String id;
    private String logpoint;
    private String operation;
    private long sequence;
    private List<GLGEquip> equipments;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogpoint() {
        return logpoint;
    }

    public void setLogpoint(String logpoint) {
        this.logpoint = logpoint;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public List<GLGEquip> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<GLGEquip> equipments) {
        this.equipments = equipments;
    }
    
    
    public String toString() {
        return new Gson().toJson(this);
    }
}
