/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.techmania.glgservice.web;

import com.ti.techmania.glgservice.domain.GLGData;
import com.ti.techmania.glgservice.domain.GLGLotItem;
import com.ti.techmania.glgservice.domain.PaymentRequest;
import com.ti.techmania.glgservice.repository.GLGDao;
import com.ti.techmania.glgservice.service.GLGService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author a0284827
 */
@RestController
@RequestMapping("/glg")
public class GLGController {
    
    private GLGService glgService;
    
    @Autowired
    GLGController(final GLGService glgService) {
        this.glgService = glgService;
    }
    
    @RequestMapping(value = "/lotLptEquip", method = RequestMethod.POST)
    public GLGData lotLptEquip(@RequestBody String lot) {
        GLGData glgData = new GLGData();
        try {
            glgData = glgService.getGlgData(lot);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return glgData;
    }
}
