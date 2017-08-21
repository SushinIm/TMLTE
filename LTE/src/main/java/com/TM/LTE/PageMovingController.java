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
<<<<<<< HEAD
	@RequestMapping(value = "/adminpage", method = RequestMethod.GET)
	public ModelAndView adminpage() {
		mav = new ModelAndView();
		mav.setViewName("adminpage");
		return mav;
	}
=======

>>>>>>> 5dea1fb7d4188a66214634538c785d394404db0a
	@RequestMapping(value = "/insertProdFrm", method = RequestMethod.GET)
	public ModelAndView insertProdFrm() {
		mav = new ModelAndView();
		mav.setViewName("hotelInsert");
		return mav;
	}
}
