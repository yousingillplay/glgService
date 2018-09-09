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
public class GLGLotItem implements Serializable {
    
    private String id;
    private String lot;
    private String lptOpnSeqId;
    private String facility;
    private String equipment;
    private String tranDttm;
    private String loginDttm;
    private List<String> parentIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getLptOpnSeqId() {
        return lptOpnSeqId;
    }

    public void setLptOpnSeqId(String lptOpnSeqId) {
        this.lptOpnSeqId = lptOpnSeqId;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTranDttm() {
        return tranDttm;
    }

    public void setTranDttm(String tranDttm) {
        this.tranDttm = tranDttm;
    }

    public String getLoginDttm() {
        return loginDttm;
    }

    public void setLoginDttm(String loginDttm) {
        this.loginDttm = loginDttm;
    }

    public List<String> getParentIds() {
        return parentIds;
    }

    public void setParentIds(List<String> parentIds) {
        this.parentIds = parentIds;
    }

    
    public String toString() {
        return new Gson().toJson(this);
    }
}
