//$Id: LotService.java,v 1.3 2012/11/16 22:09:31 a0199948 Exp $
package com.ti.techmania.glgservice.service;

import com.ti.techmania.glgservice.domain.Lot;
import java.util.List;

/**
 * Lot service definition. The service layer is where the application's
 * business logic resides (business logic and DAO interaction should normally
 * not appear in the controller).
 * TODO: remove (demonstration only)
 */
public interface LotService {

    /**
     * Gets the list of lots.
     * @param facility The facility identifier.
     * @return A List of Lot objects.
     */
    List<Lot> getLotList(String facility);

    /**
     * Gets a particular lot's details.
     * @param facility The facility identifier.
     * @param lot The lot number.
     * @return The Lot object.
     */
    Lot getLot(String facility, String lot);

    /**
     * Updates the lot details.
     * @param facility The facility identifier.
     * @param lot The lot number.
     */
    void setLot(String facility, Lot lot);

    /**
     * Gets the total number of lots.
     * @param facility The facility identifier.
     * @return The number of lots.
     */
    int getLotTotal(String facility);

    /**
     * Gets the number of lots that match some search criteria.
     * @param facility The facility identifier.
     * @param lotSearch The lot search string
     * @param lptSearch The logpoint search string
     * @param qtySearch The quantity search string
     * @return The number of lots.
     */
    int getMatchingLotTotal(String facility,
            String lotSearch, String lptSearch, String qtySearch);

    /**
     * Returns a subset of the lots that match some search criteria.
     * @param facility The facility identifier.
     * @param start The first row from the matching lots to return
     * @param length The number of rows from the matching lots to return
     * @param sortCol The column to sort the results by
     * @param sortDir Either "asc" or "desc" for ascending or descending sort
     * @param lotSearch The lot search string
     * @param lptSearch The logpoint search string
     * @param qtySearch The quantity search string
     * @return The number of lots.
     */
    List<Lot> getMatchingLots(String facility, int start,
            int length, int sortCol,  String sortDir, String lotSearch,
            String lptSearch, String qtySearch);

}
