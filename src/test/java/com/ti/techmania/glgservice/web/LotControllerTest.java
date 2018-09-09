//$Id: LotControllerTest.java,v 1.3 2015/09/10 16:56:20 a0199948 Exp $
package com.ti.techmania.glgservice.web;

import com.ti.techmania.glgservice.domain.Lot;
import com.ti.techmania.glgservice.service.LotService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for LotController.
 * TODO: remove (demonstration only)
 */
@SuppressWarnings("unchecked")
public class LotControllerTest {
    //---- Members
    @Mock
    private LotService lotService;
    @Mock
    private BindingResult result;

    private LotController lotController;

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

        // Define the behavior of the LotService mock
        when(lotService.getLotList(anyString())).thenReturn(lotList);
        // If you use matchers, you'll need them on each argument
        when(lotService.getLot(anyString(), eq("1234567"))).thenReturn(lot);

		lotController = new LotController(lotService);
    }

	@Test
	public void testListLots() {
        // Exercise controller
		Model model = new ExtendedModelMap();
        lotController.listlots(model, "DP1DM5");
        // Verify behavior
        verify(lotService, times(1)).getLotList("DP1DM5");
        // Verify results
        List<Lot> lots = (List<Lot>)model.asMap().get("lots");
        assertEquals(1, lots.size());
        assertEquals("1234567", lots.get(0).getLot());
	}

	@Test
	public void testGetLotDetails() {
		Model model = new ExtendedModelMap();
        // Exercise controller
        lotController.getLotDetails(model, "DP1DM5", "1234567");
        // Verify behavior
        verify(lotService, times(1)).getLot("DP1DM5", "1234567");
        // Verify results
        Lot lot = (Lot)model.asMap().get("lot");
        assertEquals("1234567", lot.getLot());
	}

	@Test
	public void testSetLotDetails() {
        // Exercise controller
        lotController.setLotDetails(new ExtendedModelMap(),
                "DP1DM5", new Lot(), result);
        // Verify behavior
        verify(lotService, times(1)).setLot(eq("DP1DM5"), any(Lot.class));
	}
}
