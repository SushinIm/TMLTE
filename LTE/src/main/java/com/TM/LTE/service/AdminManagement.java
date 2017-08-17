package com.TM.LTE.service;

import java.util.List;

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
		String view=null;
		mav=new ModelAndView();
		List<Member> mlist=null;//<>이름은 bean이름, mlist:멤버리스트
		
		//if(session!=null && session.getAttribute("id")!=null) //로그인여부확인
		
		mlist=mDao.adminSeller();
		
		StringBuilder sb=new StringBuilder();
		
		for(int i = 0;i<mlist.size();i++){
			sb.append("<tr><td>m_bnum</td><td>m_part</td><td>m_name</td><td>m_phone</td></tr>");
		}
		mav.addObject("mlist", mlist);
	
		
		
		
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
