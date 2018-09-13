//$Id: LotServiceImpl.java,v 1.4 2012/11/20 23:26:50 a0199948 Exp $
package com.ti.techmania.glgservice.service;

import com.ti.techmania.glgservice.domain.GLGAdjLotData;
import com.ti.techmania.glgservice.domain.GLGData;
import com.ti.techmania.glgservice.domain.GLGEquip;
import com.ti.techmania.glgservice.domain.GLGLotItem;
import com.ti.techmania.glgservice.domain.GLGLptOpnSeq;
import com.ti.techmania.glgservice.domain.Lot;
import com.ti.techmania.glgservice.repository.GLGDao;
import com.ti.techmania.glgservice.repository.LotDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of lot service. The service layer is where the application's
 * business logic resides (business logic and DAO interaction should not
 * appear in the controller).
 * TODO: remove (demonstration only)
 */
@Service
public class GLGServiceImpl implements GLGService {
    
    private GLGDao glgDao;
    
    @Autowired
    GLGServiceImpl(final GLGDao glgDao) {
        this.glgDao = glgDao;
    }
    
    @Transactional(
            readOnly = false
          , propagation = Propagation.REQUIRED
          , rollbackFor = SQLException.class)
    public GLGData getGlgData(String lot)
    {
        glgDao.callGLGGenData(lot);
        List<GLGLotItem> glgLotItems = glgDao.getGLGLotItems();
        
        for (GLGLotItem glgLotItem : glgLotItems) {
            List<String> glgParents = glgDao.getGLGParents(glgLotItem.getId());
            glgLotItem.setParentIds(glgParents);
        }
        
        List<GLGLptOpnSeq> glgLptOpnSeqs = glgDao.getLptOpnSeq();
        
        for (GLGLptOpnSeq glgLptOpnSeq : glgLptOpnSeqs) {
            List<String> equipments = glgDao.getEquipments(glgLptOpnSeq.getId());
            List<GLGEquip> glgEquips = new ArrayList<GLGEquip>();
            for (String equipment : equipments) {
                List<String> lotIds = glgDao.getLotIds(glgLptOpnSeq.getId(), equipment);
                List<GLGAdjLotData> adjacentLots = glgDao.getAdjacentLots(equipment);
                Float commonalityPercent = glgDao.getCommonality(glgLptOpnSeq.getLogpoint(), glgLptOpnSeq.getOperation(), equipment);
                GLGEquip glgEquip = new GLGEquip();
                glgEquip.setEquipment(equipment);
                glgEquip.setLotIds(lotIds);
                glgEquip.setAdjacentLots(adjacentLots);
                glgEquip.setCommonalityPercent(commonalityPercent);
                glgEquips.add(glgEquip);
            }
            
            glgLptOpnSeq.setEquipments(glgEquips);
        }
        
        GLGData glgData = new GLGData();
        glgData.setGlgData(glgLotItems);
        glgData.setLogpointOrder(glgLptOpnSeqs);
        
        return glgData;
    }
}
