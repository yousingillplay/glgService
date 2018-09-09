//$Id: LotDao.java,v 1.5 2012/11/20 23:26:49 a0199948 Exp $
package com.ti.techmania.glgservice.repository;

import com.ti.spring.annotations.MyBatisRepository;
import com.ti.techmania.glgservice.domain.Lot;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * DAO interface for working with Lots.
 * TODO: remove (demonstration only).
 */
@MyBatisRepository
public interface LotDao {

    /**
     * Gets the list of lots.
     * @param facility The facility identifier.
     * @return A List of Lot objects.
     */
    List<Lot> getLotList(String facility);

    /**
     * Gets a particular lot. Note the use of @Param in this and the methods
     * below to create the keys for the parameter map passed to MyBatis.
     * @param facility The facility identifier.
     * @param lot The lot number.
     * @return The Lot object.
     */
    Lot getLot(@Param("facility") String facility, @Param("lot") String lot);

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
    int getMatchingLotTotal(@Param("facility") String facility,
            @Param("lotSearch") String lotSearch,
            @Param("lptSearch") String lptSearch,
            @Param("qtySearch") String qtySearch);

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
    List<Lot> getMatchingLots(@Param("facility") String facility,
            @Param("start") int start,
            @Param("length") int length,
            @Param("sortCol") int sortCol,
            @Param("sortDir") String sortDir,
            @Param("lotSearch") String lotSearch,
            @Param("lptSearch") String lptSearch,
            @Param("qtySearch") String qtySearch);

}
