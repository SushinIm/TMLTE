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
	@RequestMapping(value = "/adminpage", method = RequestMethod.GET)
	public ModelAndView adminpage() {
		mav = new ModelAndView();
		mav.setViewName("adminpage");
		return mav;
	}
	@RequestMapping(value = "/airinsertFrm", method = RequestMethod.GET)
	public ModelAndView airinsertFrm() {
		mav = new ModelAndView();
		System.out.println("airinsertFrm");
		mav.setViewName("airinsert");
		return mav;
	}
	@RequestMapping(value = "/hotelinsertFrm", method = RequestMethod.GET)
	public ModelAndView insertProdFrm() {
		mav = new ModelAndView();
		System.out.println("hotelinsertFrm");
		mav.setViewName("hotelinsert");
		return mav;
	}
}
