/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.techmania.glgservice.repository;

import com.ti.spring.annotations.MyBatisRepository;
import com.ti.techmania.glgservice.domain.GLGAdjLotData;
import com.ti.techmania.glgservice.domain.GLGLotItem;
import com.ti.techmania.glgservice.domain.GLGLptOpnSeq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
    List<String> getEquipments(final String lptOpnSeqId);
    List<String> getLotIds(@Param("lptOpnSeqId") final String lptOpnSeqId,
                           @Param("equipment") final String equipment);
    List<GLGAdjLotData> getAdjacentLots(final String equipment);
    Float getCommonality(@Param("logpoint") final String logpoint, 
                         @Param("operation") final String operation,
                         @Param("equipment") final String equipment);
}
