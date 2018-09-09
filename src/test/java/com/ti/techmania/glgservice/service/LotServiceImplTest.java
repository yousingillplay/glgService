//$Id: LotServiceImplTest.java,v 1.2 2015/09/10 16:56:20 a0199948 Exp $
package com.ti.techmania.glgservice.service;

import com.ti.techmania.glgservice.domain.Lot;
import com.ti.techmania.glgservice.repository.LotDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for LotServiceImpl.
 * TODO: remove (demonstration only)
 */
public class LotServiceImplTest {
    //---- Members
    @Mock
    private LotDao lotDao;

    private LotService lotService;

    //---- Methods
    @Before
    public void setup() {
        // Create the mocks
        MockitoAnnotations.initMocks(this);

        // Create a Lot object and list
        Lot lot = new Lot();
        lot.setLot("1234567");
        lot.setCurQty(1);
        lot.setLpt("1234");
        List<Lot> lotList = new ArrayList<Lot>();
        lotList.add(lot);

        // Define the behavior of the LotDao mock
        when(lotDao.getLotList(anyString())).thenReturn(lotList);
        // If you use matchers, you'll need them on each argument
        when(lotDao.getLot(anyString(), eq("1234567"))).thenReturn(lot);

		lotService = new LotServiceImpl(lotDao);
    }

	@Test
	public void testGetLotList() {
        // Exercise controller
        List<Lot> lots = lotService.getLotList("DP1DM5");
        // Verify behavior
        verify(lotDao, times(1)).getLotList("DP1DM5");
        // Verify results
        assertEquals(1, lots.size());
        assertEquals("1234567", lots.get(0).getLot());
	}

	@Test
	public void testGetLot() {
        // Exercise controller
        Lot lot = lotService.getLot("DP1DM5", "1234567");
        // Verify behavior
        verify(lotDao, times(1)).getLot("DP1DM5", "1234567");
        // Verify results
        assertEquals("1234567", lot.getLot());
	}

	@Test
	public void testSetLot() {
        // Exercise controller
        lotService.setLot("DP1DM5", new Lot());
        // Verify behavior

	}
}
