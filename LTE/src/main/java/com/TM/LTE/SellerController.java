package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.service.SellerManagement;


@Controller
public class SellerController {
	@Autowired
	private SellerManagement sm;
	
	private ModelAndView mav;   
	
	@RequestMapping(value = "/mypageFrm", method = RequestMethod.GET)
	public ModelAndView mypageFrm() {
		System.out.println("mypageFrm");
		mav=new ModelAndView();
		mav = sm.execute(1);
		//mav.setViewName("sellerpage");//.jsp
		return mav;
	}
	@RequestMapping(value = "/bestProd", method = RequestMethod.GET)
	public ModelAndView bestProd() {
		System.out.println("bestProd");
		mav=new ModelAndView();
		mav = sm.execute(2);
		//mav.setViewName("sellerpage");//.jsp
		return mav;
	}
}
