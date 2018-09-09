/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.techmania.glgservice.repository;

import com.ti.spring.annotations.MyBatisRepository;
import com.ti.techmania.glgservice.domain.GLGLotItem;
import com.ti.techmania.glgservice.domain.GLGLptOpnSeq;
import java.util.List;

/**
 *
 * @author a0284827
 */
@MyBatisRepository
public interface GLGDao {
    void callGLGGenData(String lot);
    List<GLGLotItem> getGLGLotItems();
    List<String> getGLGParents(final String id);
    List<GLGLptOpnSeq> getLptOpnSeq();
}
