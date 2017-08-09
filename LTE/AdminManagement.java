package com.TM.LTE.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Member;
import com.TM.LTE.dao.MemberDao;

@Service
public class AdminManagement {
	@Autowired
	private MemberDao mDao;
	@Autowired
	private HttpSession session;

	private ModelAndView mav;

	public ModelAndView execute(Member mb,int cmd) {
		mav = new ModelAndView();
		switch(cmd){
		case 1:
			adminSeller(mb);
			break;
		case 2:
			adminBuyer(mb);
			break;
		case 3:
			adminBlack(mb);
			break;
		case 4:
			adminNotice(mb);
			break;
		case 5:
			adminHistory(mb);
			break;
		}
		return mav;

	}

	private void adminSeller(Member mb) {
		
	}

	private void adminBuyer(Member mb) {

	}

	private void adminBlack(Member mb) {

	}

	private void adminNotice(Member mb) {

	}

	private void adminHistory(Member mb) {

	}


}
