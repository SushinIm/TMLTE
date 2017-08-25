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

	@RequestMapping(value = "/tkdetail", method = RequestMethod.GET)
	public ModelAndView tkdetail() {
		mav = new ModelAndView();
		mav.setViewName("ticketdetail");
		return mav;
	}

	@RequestMapping(value = "/ticketBuying", method = RequestMethod.GET)
	public ModelAndView buyTicket() {
		mav = rm.executeTicket(1);
		return mav;
	}  
	
	@RequestMapping(value = "/htdetail", method = RequestMethod.GET)
	public ModelAndView htdetail() {
		mav = new ModelAndView();
		mav.setViewName("hoteldetail");
		return mav;
	}

	@RequestMapping(value = "/hotelBooking", method = RequestMethod.GET)
	public ModelAndView checkRoom() {
		mav = rm.executeHotel(1);
		return mav;
	}  

	@RequestMapping(value = "/hotelReserve", method = RequestMethod.GET)
	public ModelAndView reserveHotel() {
		mav = rm.executeHotel(2);
		return mav;
	}  

	@RequestMapping(value = "/ardetail", method = RequestMethod.GET)
	public ModelAndView toTicket() {
		mav = new ModelAndView();
		mav.setViewName("airdetail");
		return mav;
	}
	
	@RequestMapping(value = "/airReserve", method = RequestMethod.GET)
	public ModelAndView reserveAir() {
		mav = rm.executeAir(1);
		return mav;
	}

	@RequestMapping(value = "/addPass", method = RequestMethod.GET)
	public ModelAndView addPass() {
		mav = rm.executeAir(2);
		return mav;
	}
	
	@RequestMapping(value = "/payAir", method = RequestMethod.GET)
	public ModelAndView payAir() {
		mav = rm.executeAir(3);
		return mav;
	}
}
