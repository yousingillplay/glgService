//$Id: HomeControllerTest.java,v 1.4 2015/10/19 20:12:46 a0199948 Exp $
package com.ti.techmania.glgservice.web;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.*;

/**
 * Unit tests for HomeController.
 * TODO: remove (demonstration only)
 */
public class HomeControllerTest {

	@Test
	public void testController() {
		HomeController controller = new HomeController();
		Model model = new ExtendedModelMap();
		assertEquals("hello", controller.hello(model, "some choice"));

		Object message = model.asMap().get("controllerMessage");
		assertEquals("This is the message from the controller!",message);

		Object choice = model.asMap().get("choice");
		assertEquals("some choice",choice);

	}
}
