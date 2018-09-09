//$Id: LotServiceImpl.java,v 1.4 2012/11/20 23:26:50 a0199948 Exp $
package com.ti.techmania.glgservice.service;

import com.ti.techmania.glgservice.domain.Lot;
import com.ti.techmania.glgservice.repository.LotDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of lot service. The service layer is where the application's
 * business logic resides (business logic and DAO interaction should not
 * appear in the controller).
 * TODO: remove (demonstration only)
 */
@Service
public class LotServiceImpl implements LotService {
    //---- Members
    private LotDao lotDao;

    //---- Methods
    /**
     * Main constructor.
     * @param lotDao The LotDao implementation
     */
    @Autowired
    LotServiceImpl(final LotDao lotDao) {
        this.lotDao = lotDao;
    }

    /** {@inheritDoc} */
    public List<Lot> getLotList(final String facility) {
        return lotDao.getLotList(facility);
    }

    /** {@inheritDoc} */
    public Lot getLot(final String facility, final String lot) {
        return lotDao.getLot(facility, lot);
    }

    /** {@inheritDoc} */
    @Transactional
    public void setLot(final String facility, final Lot lot) {
        // Not implemented; simulate a lengthy transaction.
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException ex) {
            // Ignored
        }
    }

    /** {@inheritDoc} */
    public int getLotTotal(final String facility) {
        return lotDao.getLotTotal(facility);
    }

    /** {@inheritDoc} */
    public int getMatchingLotTotal(final String facility,
            final String lotSearch,
            final String lptSearch,
            final String qtySearch) {
        return lotDao.getMatchingLotTotal(facility, lotSearch,
                lptSearch, qtySearch);
    }

    /** {@inheritDoc} */
    public List<Lot> getMatchingLots(final String facility, final int start,
            final int length, final int sortCol,
            final String sortDir, final String lotSearch,
            final String lptSearch, final String qtySearch) {
        return lotDao.getMatchingLots(facility, start, length,
                sortCol, sortDir, lotSearch, lptSearch, qtySearch);
    }

}
