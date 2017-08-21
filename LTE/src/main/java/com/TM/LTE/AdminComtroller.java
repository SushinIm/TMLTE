package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Member;
import com.TM.LTE.service.AdminManagement;

@Controller
@SessionAttributes("member")
public class AdminComtroller {
	@Autowired  
	private AdminManagement am; //admin리스트출력 클래스
	
	private ModelAndView mav;
	
	@RequestMapping(value = "/adminseller", method = RequestMethod.GET)
	public ModelAndView adminseller(Member mb) {
		mav=am.execute(mb,1);
		return mav;
	}
	@RequestMapping(value = "/adminbuyer", method = RequestMethod.GET)
	public ModelAndView adminbuyer(Member mb) {
		mav=am.execute(mb,2);
		return mav;
	}
	@RequestMapping(value = "/adminblack", method = RequestMethod.GET)
	public ModelAndView adminblack(Member mb) {
		mav=am.execute(mb,3);
		return mav;
	}
	@RequestMapping(value = "/adminnotice", method = RequestMethod.GET)
	public ModelAndView adminnotice(Member mb) {
		mav=am.execute(mb,4);
		return mav;
	}
	@RequestMapping(value = "/adminhistory", method = RequestMethod.GET)
	public ModelAndView adminhistory(Member mb) {
		mav=am.execute(mb,5);
		return mav;
	}
	@RequestMapping(value = "/blackseller", method = RequestMethod.GET)
	public ModelAndView blackSeller(Member mb) {
		System.out.println("진입");
		mav=am.execute(mb,6);
		return mav;
	}
}
