package com.TM.LTE;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.service.ReserveManagement;

@Controller
public class ReserveController {
	private ModelAndView mav;   
	
	@Autowired
	private ReserveManagement rm;

	@RequestMapping(value = "/airInternals", method = RequestMethod.GET)
	public ModelAndView internalTour() {
		mav = new ModelAndView();
		mav.setViewName("internalair");
		return mav;
	}

	@RequestMapping(value = "/hotelInternals", method = RequestMethod.GET)
	public ModelAndView internalHotel() {
		mav = new ModelAndView();
		mav.setViewName("internalhotel");
		return mav;
	}

	@RequestMapping(value = "/ticketInternals", method = RequestMethod.GET)
	public ModelAndView internalTicket() {
		mav = new ModelAndView();
		mav.setViewName("internalticket");
		return mav;
	}

	@RequestMapping(value = "/airOverseas", method = RequestMethod.GET)
	public ModelAndView toAir() {
		mav = new ModelAndView();
		mav.setViewName("overseasair");
		return mav;
	}

	@RequestMapping(value = "/hotelOverseas", method = RequestMethod.GET)
	public ModelAndView toHotel() {
		mav = new ModelAndView();
		mav.setViewName("overseashotel");
		return mav;
	}

	@RequestMapping(value = "/ticketOverseas", method = RequestMethod.GET)
	public ModelAndView toTicket() {
		mav = new ModelAndView();
		mav.setViewName("overseasticket");
		return mav;
	}

	@RequestMapping(value = "/hotelBooking", method = RequestMethod.GET)
	public ModelAndView reserveHotel() {
		mav = new ModelAndView();
		mav = rm.execute(8);
		return mav;
	}  

	@RequestMapping(value = "/ticketBuying", method = RequestMethod.GET)
	public ModelAndView buyTicket() {
		mav = new ModelAndView();
		mav = rm.execute(9);
		return mav;
	}  
	

}
