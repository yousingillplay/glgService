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
public class GLGData implements Serializable {
    
    private List<GLGLotItem> glgData;
    private List<GLGLptOpnSeq> logpointOrder;

    public List<GLGLotItem> getGlgData() {
        return glgData;
    }

    public void setGlgData(List<GLGLotItem> glgData) {
        this.glgData = glgData;
    }

    public List<GLGLptOpnSeq> getLogpointOrder() {
        return logpointOrder;
    }

    public void setLogpointOrder(List<GLGLptOpnSeq> logpointOrder) {
        this.logpointOrder = logpointOrder;
    }
    
    public String toString() {
        return new Gson().toJson(this);
    }
}
