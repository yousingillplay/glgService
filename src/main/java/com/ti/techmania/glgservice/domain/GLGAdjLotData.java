/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.techmania.glgservice.domain;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 *
 * @author a0284827
 */
public class GLGAdjLotData implements Serializable {
    private String lotUnderInv;
    private String adjacentLot;
    private String equipment;
    private String startDttm;
    private String loginDttm;
    private String adjLoginDttm;
    private String currentLogpoint;
    private String lastLogpoint;
    private String lastActDttm;
    private String isAutomotive;

    public String getLotUnderInv() {
        return lotUnderInv;
    }

    public void setLotUnderInv(String lotUnderInv) {
        this.lotUnderInv = lotUnderInv;
    }

    public String getAdjacentLot() {
        return adjacentLot;
    }

    public void setAdjacentLot(String adjacentLot) {
        this.adjacentLot = adjacentLot;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getStartDttm() {
        return startDttm;
    }

    public void setStartDttm(String startDttm) {
        this.startDttm = startDttm;
    }

    public String getLoginDttm() {
        return loginDttm;
    }

    public void setLoginDttm(String loginDttm) {
        this.loginDttm = loginDttm;
    }

    public String getAdjLoginDttm() {
        return adjLoginDttm;
    }

    public void setAdjLoginDttm(String adjLoginDttm) {
        this.adjLoginDttm = adjLoginDttm;
    }

    public String getCurrentLogpoint() {
        return currentLogpoint;
    }

    public void setCurrentLogpoint(String currentLogpoint) {
        this.currentLogpoint = currentLogpoint;
    }

    public String getLastLogpoint() {
        return lastLogpoint;
    }

    public void setLastLogpoint(String lastLogpoint) {
        this.lastLogpoint = lastLogpoint;
    }

    public String getLastActDttm() {
        return lastActDttm;
    }

    public void setLastActDttm(String lastActDttm) {
        this.lastActDttm = lastActDttm;
    }

    public String getIsAutomotive() {
        return isAutomotive;
    }

    public void setIsAutomotive(String isAutomotive) {
        this.isAutomotive = isAutomotive;
    }
    
    public String toString() {
        return new Gson().toJson(this);
    }
}
