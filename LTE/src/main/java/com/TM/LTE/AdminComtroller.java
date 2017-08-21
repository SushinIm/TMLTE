package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Member;
import com.TM.LTE.service.AdminManagement;

@Controller
//@SessionAttributes("member")
public class AdminComtroller {
	@Autowired
	private AdminManagement am; //admin리스트출력 클래스
	
	private ModelAndView mav;
	
	@RequestMapping(value = "/adminSeller", method = RequestMethod.GET)
	public ModelAndView adminseller(Member mb) {
		mav=am.execute(mb,1);
		return mav;
	}
	@RequestMapping(value = "/adminBuyer", method = RequestMethod.GET)
	public ModelAndView adminbuyer(Member mb) {
		mav=am.execute(mb,2);
		return mav;
	}
	@RequestMapping(value = "/adminBlack", method = RequestMethod.GET)
	public ModelAndView adminblack(Member mb) {
		mav=am.execute(mb,3);
		return mav;
	}
	@RequestMapping(value = "/adminNotice", method = RequestMethod.GET)
	public ModelAndView adminnotice(Member mb) {
		mav=am.execute(mb,4);
		return mav;
	}
	@RequestMapping(value = "/adminHistory", method = RequestMethod.GET)
	public ModelAndView adminhistory(Member mb) {
		mav=am.execute(mb,5);
		return mav;
	}
}
