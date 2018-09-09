//$Id: HomeController.java,v 1.6 2012/05/09 18:52:15 a0199948 Exp $
package com.ti.techmania.glgservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Sample controller.
 * TODO: remove (demonstration only)
 */
@Controller
public class HomeController {
    //---- Members
    // Pulls the "extraText" property value into the extraText member variable
    @Value("#{appProperties.extraText}")
    private String extraText;

    //---- Methods
    /**
     * Redirect to the about page.
     * @param model The data model
     * @return The view name - &quot;about&quot;
     */
	@RequestMapping("/")
	public String hello(final Model model) {
		return "about";
	}

	/**
	 * Selects the hello page and populates the model with a message.
     * @param model The data model
     * @param choice The value of the &quot;choice&quot; request parameter
     * @return The view name - &quot;hello&quot;
	 */
	@RequestMapping("/hello")
	public String hello(final Model model,
            @RequestParam(value="choice",required=false) final String choice) {
        model.addAttribute("choice", choice);
		model.addAttribute("controllerMessage",
				"This is the message from the controller!");
        model.addAttribute("extraText", extraText);
		return "hello";
	}

	/**
	 * Selects the goodbye page.
     * @param model The data model
     * @param choice The value of the &quot;choice&quot; path variable
     * @return The view name - &quot;goodbye&quot;
	 */
	@RequestMapping("/goodbye/{choice}")
	public String goodbye(final Model model,
            @PathVariable("choice") final String choice) {
        model.addAttribute("choice", choice);
		model.addAttribute("controllerMessage",
				"The controller bids you adieu!");
		return "goodbye";
	}

}
