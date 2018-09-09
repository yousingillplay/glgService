//$Id: LotService.java,v 1.3 2012/11/16 22:09:31 a0199948 Exp $
package com.ti.techmania.glgservice.service;

import com.ti.techmania.glgservice.domain.GLGData;
import java.util.List;

/**
 * Lot service definition. The service layer is where the application's
 * business logic resides (business logic and DAO interaction should normally
 * not appear in the controller).
 * TODO: remove (demonstration only)
 */
public interface GLGService {
    GLGData getGlgData(String lot);
}
