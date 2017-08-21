package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.BuyerBlack;
import com.TM.LTE.bean.Member;
import com.TM.LTE.dao.MemberDao;

@Service
public class AdminManagement {
	private ModelAndView mav;

	@Autowired
	private MemberDao mDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest req;

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
		case 6:
			blackSeller(mb);
			break;
		/*case 7:
			blackBuyer(mb);
			break;*/
		}
		return mav;

	}

	//------------------------------------------------------------
	private void adminSeller(Member mb) {
		String view=null;
		mav=new ModelAndView();
		List<Member> mlist=null;//<>이름은 bean이름, mlist:멤버리스트
		mlist=mDao.adminSeller();
		String mlist2=makehtml(mlist);
		mav.addObject("mlist", mlist2);
		mav.setViewName("adminseller");
	}


	private String makehtml(List<Member> mlist) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>회원번호</td><td>구분</td><td>이름</td><td>전화번호</td><td>사유</td><td>블랙리스트</td></tr>");
		for(int i = 0;i<mlist.size();i++){
			Member mbb=mlist.get(i);
			sb.append("<tr><td>"+mbb.getM_bnum()+"</td>");
			sb.append("<td>"+mbb.getM_part()+"</td>");
			sb.append("<td>"+mbb.getM_name()+"</td>");
			sb.append("<td>"+mbb.getM_phone()+"</td>");
			sb.append("<td><select name='select'><option value='허위 또는 과장광고'>허위 또는 과장광고</option><option value='22블랙사유22'>22블랙사유22</option><option value='333블랙사유333'>333블랙사유333</option></select></td>");
			sb.append("<td><button onclick='addblack("+mbb.getM_bnum()+")'>추가</button></td></tr>");

		}
		sb.append("</table>");
		return sb.toString();
	}


//--------------------------------------------------
	private void adminBuyer(Member mb) {
		String view=null;
		//mav=new ModelAndView();
		List<Member> mlist=null;//<>이름은 bean이름, mlist:멤버리스트
		mlist=mDao.adminBuyer();
		String mlist2=makehtml1(mlist);
		mav.addObject("mlist", mlist2);
		mav.setViewName("adminbuyer");
	}
	
	private String makehtml1(List<Member> mlist) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>회원번호</td><td>이름</td><td>ID</td><td>전화번호</td><td>생년월일</td><td>사유</td><td>블랙리스트</td></tr>");
		for(int i = 0;i<mlist.size();i++){
			Member mbb=mlist.get(i);
			sb.append("<tr><td>"+mbb.getM_bnum()+"</td>");
			sb.append("<td>"+mbb.getM_name()+"</td>");
			sb.append("<td>"+mbb.getM_id()+"</td>");
			sb.append("<td>"+mbb.getM_phone()+"</td>");
			sb.append("<td>"+mbb.getM_birth()+"</td>");
			sb.append("<td><select name='select'><option value='블랙사유1'>블랙사유1</option><option value='22블랙사유22'>22블랙사유22</option><option value='333블랙사유333'>333블랙사유333</option></select></td>");
			sb.append("<td><button onclick='addblack("+mbb.getM_bnum()+")'>추가</button></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	

	
//--------------------------------------------------
	private void adminBlack(Member mb) {
		List<Member> bslist = mDao.blackSeller();
		String mlist1 = makebsList(bslist);
		mav.addObject("mlist",mlist1);
		mav.setViewName("adminblack");







	}

	private void adminNotice(Member mb) {

	}

	private void adminHistory(Member mb) {

	}

	private void blackSeller(Member mb) {
		System.out.println("블랙리스트 클릭");
		String m_bnum = req.getParameter("m_bnum");
		System.out.println(req.getParameter("stdate"));
		mb = mDao.selectmid(m_bnum);
		mb.setB_mid(mb.getM_id());
		System.out.println(mb.getB_mid());
		mb.setB_date(req.getParameter("stdate"));
		System.out.println(mb.getB_date());
		mb.setB_reason(req.getParameter("reason"));
		System.out.println(mb.getB_reason());
		mDao.addBlack(mb);
		List<Member> bslist = mDao.blackSeller();
		String mlist1 = makebsList(bslist);
		mav.addObject("mlist",mlist1);
		mav.setViewName("adminblack");
	}
	private String makebsList(List<Member> bslist){
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<table><tr><td>사업자번호</td><td>구분</td><td>ID</td><td>상호명</td><td>전화번호</td><td>생년월일</td><td>사유</td><td>블랙리스트된 등록날짜</td></tr>");
		for(int i=0;i<bslist.size();i++){
			Member mb=bslist.get(i);
			sb.append("<tr><td>"+mb.getM_bnum()+"</td>");
			sb.append("<td>"+mb.getM_part()+"</td>");
			sb.append("<td>"+mb.getB_mid()+"</td>");
			sb.append("<td>"+mb.getM_name()+"</td>");
			sb.append("<td>"+mb.getM_phone()+"</td>");
			sb.append("<td>"+mb.getM_birth()+"</td>");
			sb.append("<td>"+mb.getB_reason()+"</td>");
			sb.append("<td>"+mb.getB_date()+"</td>");
			sb.append("<td><button>복구</button></td></tr>");

		}
		sb.append("</table>");
		return sb.toString();
	}
	
	/*private void blackBuyer(Member mb) {
		BuyerBlack bb=new BuyerBlack();
		String m_bnum = req.getParameter("m_bnum");
		bb = mDao.selectbbList(m_bnum);
		bb.setM_stop(req.getParameter("stdate"));
		bb.setM_reason(req.getParameter("reason"));
		mDao.addBlack(bb);
		List<BuyerBlack> bbList =mDao.getBlackList2();
		List<Member> mlist = mDao.adminBuyer();
		String bblist = makeBBList(bbList, mlist);
		mav.addObject("bblist",bblist);
		mav.setViewName("adminblack");
	}
	private String makeBBList(List<BuyerBlack> bbList, List<Member> mlist){
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		for(int i=0;i<bbList.size();i++){
			Member mbb=mlist.get(i);
			BuyerBlack bb = bbList.get(i);
			sb.append("<tr><td>"+mbb.getM_bnum()+"</td>");
			sb.append("<td>"+mbb.getM_part()+"</td>");
			sb.append("<td>"+mbb.getM_name()+"</td>");
			sb.append("<td>"+mbb.getM_phone()+"</td>");
			sb.append("<td>"+bb.getB_date()+"</td>");
			sb.append("<td>"+bb.getB_reason()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}*/
}