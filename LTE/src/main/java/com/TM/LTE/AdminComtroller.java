package com.TM.LTE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminComtroller {
	
	private ModelAndView mav;
	
	@RequestMapping(value = "/adminSeller", method = RequestMethod.GET)
	public ModelAndView adminseller() {
		mav = new ModelAndView();
		mav.setViewName("adminseller");
		return mav;
	}
	@RequestMapping(value = "/adminBuyer", method = RequestMethod.GET)
	public ModelAndView adminbuyer() {
		mav = new ModelAndView();
		mav.setViewName("adminbuyer");
		return mav;
	}
	@RequestMapping(value = "/adminBlack", method = RequestMethod.GET)
	public ModelAndView adminblack() {
		mav = new ModelAndView();
		mav.setViewName("adminblack");
		return mav;
	}
	@RequestMapping(value = "/adminNotice", method = RequestMethod.GET)
	public ModelAndView adminnotice() {
		mav = new ModelAndView();
		mav.setViewName("adminnotice");
		return mav;
	}
	@RequestMapping(value = "/adminHistory", method = RequestMethod.GET)
	public ModelAndView adminhistory() {
		mav = new ModelAndView();
		mav.setViewName("adminhistory");
		return mav;
	}
}
