package com.TM.LTE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageMovingController {
	private ModelAndView mav;   

	@RequestMapping(value = "/", method = RequestMethod.GET)
	   public ModelAndView main() {
	      mav = new ModelAndView();
	      mav.setViewName("main");
	      return mav;
	   }
}
