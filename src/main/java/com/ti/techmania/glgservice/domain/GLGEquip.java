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
public class GLGEquip implements Serializable {
    private String equipment;
    private Float commonalityPercent;
    private List<String> lotIds;
    private List<GLGAdjLotData> adjacentLots;

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Float getCommonalityPercent() {
        return commonalityPercent;
    }

    public void setCommonalityPercent(Float commonalityPercent) {
        this.commonalityPercent = commonalityPercent;
    }

    public List<String> getLotIds() {
        return lotIds;
    }

    public void setLotIds(List<String> lotIds) {
        this.lotIds = lotIds;
    }

    public List<GLGAdjLotData> getAdjacentLots() {
        return adjacentLots;
    }

    public void setAdjacentLots(List<GLGAdjLotData> adjacentLots) {
        this.adjacentLots = adjacentLots;
    }
    
    public String toString() {
        return new Gson().toJson(this);
    }
}
