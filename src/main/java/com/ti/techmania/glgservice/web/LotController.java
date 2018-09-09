//$Id: LotController.java,v 1.10 2018/04/03 08:04:54 a0284538 Exp $
package com.ti.techmania.glgservice.web;

import com.ti.techmania.glgservice.domain.Lot;
import com.ti.techmania.glgservice.service.LotService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Sample controller.
 * TODO: remove (demonstration only)
 */
@Controller
@Slf4j
public class LotController {
    //---- Members
    private LotService lotService;
    
    //---- Methods
    /**
     * Main constructor.
     * @param lotService The LotService implementation
     */
    @Autowired
    LotController(final LotService lotService) {
        this.lotService = lotService;
    }

    /**
     * Gets the lot list.
     * @param model The data model
     * @param facility The facility request parameter value
     * @return The view name &quot;lotlist&quot;
     */
	@RequestMapping("/listlots")
	public String listlots(final Model model,
            @RequestParam("facility") final String facility) {
        model.addAttribute("lots", lotService.getLotList(facility));
		return "lotlist";
	}

    /**
     * Gets the lot details.
     * @param model The data model
     * @param facility The facility request parameter value
     * @param lot The lot request parameter value
     * @return The view name &quot;lotDetailsDialog&quot;
     */
    @RequestMapping(value="/lotDetailsDialog", method=RequestMethod.GET)
    public String getLotDetails(final Model model,
            @RequestParam("facility") final String facility,
            @RequestParam("lot") final String lot) {
        model.addAttribute("facility", facility);
        model.addAttribute("lot", lotService.getLot(facility, lot));
        return "lotDetailsDialog";
    }

    /**
     * Sets the lot details. We use @ResponseBody to directly map the return
     * value from this handler back to the body of the Http response (skipping
     * the view resolvers). Spring delegates to a HttpMessageConverter to
     * perform the serialization of the return object. Here it invokes a
     * MappingJacksonHttpMessageConverter to create JSON; this is enabled
     * automatically when we use the mvc:annotation-driven config element
     * with Jackson in the classpath.
     * @param model The data model
     * @param facility The facility request parameter value
     * @param lot The lot request parameter value
     * @param result The object that represents binding results
     * @return A List of ObjectError instances
     */
    @RequestMapping(value="/lotDetailsDialog", method=RequestMethod.POST)
    @ResponseBody
    public List<ObjectError> setLotDetails(final Model model,
            @Valid @ModelAttribute("facility") final String facility,
            @Valid @ModelAttribute("lot") final Lot lot,
            final BindingResult result) {
         lotService.setLot(facility, lot);
         return result.getAllErrors();
    }

    /**
     * DataTable example; initial request handler. Table data is requested
     * using Ajax calls handled in the method following this one.
     * @param model The data model
     * @return The view name &quot;lottable&quot;
     */
	@RequestMapping("/lottable")
	public String lottable(final Model model) {
		return "lottable";
	}

    /**
     * Ajax handler that returns the lot list for the DataTables example.
     * See note above about the use of @ResponseBody.
     * @param facility The facility request parameter value
     * @param echo The &quot;sEcho&quot; request parameter value
     * @param sortCol The &quot;iSortCol_0&quot; request parameter value
     * @param sortDir The &quot;sSortDir_0&quot; request parameter value
     * @param lotSearch The &quot;sSearch_0&quot; request parameter value
     * @param lptSearch The &quot;sSearch_1&quot; request parameter value
     * @param qtySearch The &quot;sSearch_2&quot; request parameter value
     * @param start The &quot;iDisplayStart&quot; request parameter value
     * @param length The &quot;iDisplayLength&quot; request parameter value
     * @return A map of DataTables attributes
     */
	@RequestMapping("/lottable.json")
	@ResponseBody
    public Map<String, Object> lottable(
            @RequestParam("facility") final String facility,
            @RequestParam("sEcho") final String echo,
            @RequestParam("iSortCol_0") final int sortCol,
            @RequestParam("sSortDir_0") final String sortDir,
            @RequestParam("sSearch_0") final String lotSearch,
            @RequestParam("sSearch_1") final String lptSearch,
            @RequestParam("sSearch_2") final String qtySearch,
            @RequestParam("iDisplayStart") final int start,
            @RequestParam("iDisplayLength") final int length) {
        // Prepare the return object for DataTables rendering
        Map<String, Object> ret = new HashMap<String, Object>();
        // Unique draw count; must be echoed back to the client
        ret.put("sEcho", Integer.parseInt(echo));
        // Total number of records before filtering
        ret.put("iTotalRecords", lotService.getLotTotal(facility));
        // Total number of records after filtering
        ret.put("iTotalDisplayRecords", lotService.getMatchingLotTotal(
                facility, lotSearch, lptSearch, qtySearch));
        // The data to show in the table
        ret.put("aaData", lotService.getMatchingLots(
                facility, start, length, sortCol,
                sortDir, lotSearch, lptSearch, qtySearch));
        return ret;
    }
}
