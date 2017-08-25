package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.AirType;
import com.TM.LTE.bean.Member;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.dao.AirDao;
import com.TM.LTE.dao.MemberDao;
import com.TM.LTE.dao.SellerDao;

@Service
public class AdminManagement {
	@Autowired
	private MemberDao mDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private AirDao aDao;
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
		case 6:
			blackSeller(mb);
			break;
		case 7:
			blackBuyer(mb);
			break;
		case 8:
			deleteblack(mb);
			break;
		}
		return mav;

	}


	private void deleteblack(Member mb) {
		mDao.deleteBlack(mb);
		adminBlack(mb);
	}


	private void adminSeller(Member mb) {
		String view=null;
		mav=new ModelAndView();
		List<Member> mlist=null;//<>이름은 bean이름, mlist:멤버리스트
		mlist=mDao.adminSeller();
		String mlist2=makehtml(mlist);
		mav.addObject("sellerList", mlist2);
		mav.setViewName("adminseller");
	}

	private String makehtml(List<Member> mlist) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>사업자번호</td><td>아이디</td><td>구분</td><td>이름</td><td>전화번호</td><td>사유</td><td>블랙리스트등록</td></tr>");
		for(int i = 0;i<mlist.size();i++){
			Member mbb=mlist.get(i);
			sb.append("<tr><td>"+mbb.getM_bnum()+"</td>");
			sb.append("<td>"+mbb.getM_id()+"</td>");
			sb.append("<td>"+mbb.getM_part()+"</td>");
			sb.append("<td>"+mbb.getM_name()+"</td>");
			sb.append("<td>"+mbb.getM_phone()+"</td>");
			sb.append("<td><select class='line"+i+"' name='select'><option value='블랙사유1'>블랙사유1</option><option value='22블랙사유22'>22블랙사유22</option><option value='333블랙사유333'>333블랙사유333</option></select></td>");
			sb.append("<td><input type='button' class='line"+i+"' onclick=\"addblack('"+mbb.getM_id()+"', this)\"  value='추가'/></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	private void adminBuyer(Member mb) {
		String view=null;
		mav=new ModelAndView();
		List<Member> mlist1=null;//<>이름은 bean이름, mlist:멤버리스트
		mlist1=mDao.adminBuyer();
		String mlist3=makehtml1(mlist1);
		mav.addObject("buyerList", mlist3);
		mav.setViewName("adminbuyer");
	}

	private String makehtml1(List<Member> mlist1) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>이름</td><td>ID</td><td>전화번호</td><td>생년월일</td><td>사유</td><td>블랙리스트등록</td></tr>");
		for(int i = 0;i<mlist1.size();i++){
			Member mbb=mlist1.get(i);
			sb.append("<tr><td>"+mbb.getM_name()+"</td>");
			sb.append("<td>"+mbb.getM_id()+"</td>");
			sb.append("<td>"+mbb.getM_phone()+"</td>");
			sb.append("<td>"+mbb.getM_birth()+"</td>");
			sb.append("<td><select class='line"+i+"' name='select'><option value='블랙사유1'>블랙사유1</option><option value='22블랙사유22'>22블랙사유22</option><option value='333블랙사유333'>333블랙사유333</option></select></td>");
			sb.append("<td><input type='button' class='line"+i+"' onclick=\"addblack('"+mbb.getM_id()+"', this)\"  value='추가'/></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}


	private void adminBlack(Member mb) {
		System.out.println("1111");
		List<Member> bslist = mDao.blackSeller(mb);
		String mlist1 = makebsList(bslist);
		mav.addObject("bslist",mlist1);
		mav.setViewName("adminblack");

		System.out.println("2222");
		List<Member> bblist = mDao.blackBuyer(mb);
		String mlist2 = makebbList(bblist);
		mav.addObject("bblist",mlist2);
		mav.setViewName("adminblack");
		System.out.println("333");
	}

	private void adminNotice(Member mb) {

	}

	private void adminHistory(Member mb) {

	}


	private void blackSeller(Member mb) {
		mav = new ModelAndView();
		System.out.println("블랙리스트 클릭");
		//String m_bnum = req.getParameter("m_bnum");
		//System.out.println(req.getParameter("m_bnum"));
		mb.setB_mid(req.getParameter("m_id"));
		mb.setB_reason(req.getParameter("reason"));

		List<Member> bslist = null;
		//String id = mb.getM_id();
		//session.getAttribute("id").toString();
		if(mDao.blackSelect(mb)==0){//없다면 하위동작 실행.추가할 멤버아이디가 블랙리스트테이블에 아이디가 이미 있는지 비교하여
			System.out.println("블랙실행");
			//mDao.updateBlack(mb);
			String msg = "블랙리스트 추가되었습니다";
			mav.addObject("msg", msg);
			mDao.insertBlack(mb);
		}else{//이미 추가된 아이디라면
			mDao.blackSelect();
			String msg="이미추가된 회원입니다";
			mav.addObject("msg", msg);
			System.out.println("이미추가 msg");
		}
		bslist=mDao.adminSeller();
		String mlist2=makehtml(bslist);
		mav.addObject("sellerList", mlist2);
		mav.setViewName("adminseller");
	}

	private String makebsList(List<Member> bslist){
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<table><tr><td>사업자번호</td><td>구분</td><td>ID</td><td>상호명</td><td>전화번호</td><td>생년월일</td><td>사유</td><td>블랙리스트 등록날짜</td></tr>");
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
			sb.append("<td><input type='button' onclick=\"deleteblack('"+mb.getB_mid()+"',this)\"value='복구'/></td></tr>");

		}
		sb.append("</table>");
		return sb.toString();
	}
	private void blackBuyer(Member mb) {
		mav = new ModelAndView();
		System.out.println("블랙리스트 클릭");
		mb.setB_mid(req.getParameter("m_id"));
		mb.setB_reason(req.getParameter("reason"));
		List<Member> bblist = null;
		if(mDao.blackSelect(mb)==0){//없다면 하위동작 실행.추가할 멤버아이디가 블랙리스트테이블에 아이디가 이미 있는지 비교하여 
			mDao.insertBlack(mb);
			String msg = "블랙리스트 추가되었습니다";
			mav.addObject("msg", msg);
			System.out.println("블랙실행");
		}else{//이미 추가된 아이디라면
			System.out.println("이미블랙");
			mDao.blackSelect();
			String msg="이미추가된 회원입니다";
			mav.addObject("msg", msg);
		}
		bblist=mDao.adminBuyer();
		String mlist1 = makehtml(bblist);
		mav.addObject("forward:buyerList",mlist1);
		mav.setViewName("forward:adminbuyer");
	}
	private String makebbList(List<Member> bblist){
		StringBuilder sb=new StringBuilder();
		sb.append("<table>");
		sb.append("<table><tr><td>이름</td><td>ID</td><td>전화번호</td><td>생년월일</td><td>사유</td><td>블랙리스트 등록날짜</td></tr>");
		for(int i = 0;i<bblist.size();i++){
			Member mbb=bblist.get(i);
			sb.append("<tr><td>"+mbb.getM_name()+"</td>");
			sb.append("<td>"+mbb.getB_mid()+"</td>");
			sb.append("<td>"+mbb.getM_phone()+"</td>");
			sb.append("<td>"+mbb.getM_birth()+"</td>");
			sb.append("<td>"+mbb.getB_reason()+"</td>");
			sb.append("<td>"+mbb.getB_date()+"</td>");
			sb.append("<td><input type='button' onclick=\"deleteblack('"+mbb.getB_mid()+"',this)\"value='복구'/></td></tr>");
		}
		sb.append("</table>");;
		return sb.toString();
	}
	public ModelAndView Airexecute(int cmd) {
		mav = new ModelAndView();
		switch(cmd){
		case 1:
			airinsert(cmd);
		case 2:
			flightprod(cmd);
			break;
		}
		return mav;
	}

	private void flightprod(int cmd) {
		List<AirType> aList = null;
		aList = aDao.selectAirtype();
		String alist = flight_html(aList);
		mav = new ModelAndView();
		mav.addObject("flightlist", alist);
		mav.setViewName("flightinsert");
	}

	private String flight_html(List<AirType> aList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<select id='airtypeSelect' onChange='getSelectValue(this.form);'>");
		for(int i=0; i<aList.size(); i++){
			AirType at = aList.get(i);
			sb.append("<option value='airtype" + i + "'>"+at.getAt_airtype()+"</ooption>");
		}
		sb.append("</select>");
		return sb.toString();
	}

	private void airinsert(int cmd) {
		AirType at = new AirType();
		System.out.println(req.getParameter("airname")+"+"+req.getParameter("frow")+"+"
				+req.getParameter("fcol")+"+"+req.getParameter("brow")+"+"+req.getParameter("bcol")+"+"
				+req.getParameter("erow")+"+"+req.getParameter("ecol"));
		at.setAt_airtype(req.getParameter("airname"));
		at.setAt_frow(Integer.valueOf(req.getParameter("frow")));
		at.setAt_fcol(Integer.valueOf(req.getParameter("fcol")));
		at.setAt_brow(Integer.valueOf(req.getParameter("brow")));
		at.setAt_bcol(Integer.valueOf(req.getParameter("bcol")));
		at.setAt_erow(Integer.valueOf(req.getParameter("erow")));
		at.setAt_ecol(Integer.valueOf(req.getParameter("ecol")));
		if(aDao.insertAirtype(at)>=1){
			System.out.println("항공기 기종 insert성공");
		}else{
			System.out.println("항공기 기종 insert실패");
		}
		mav = new ModelAndView();
		mav.setViewName("airbook");
	}


}
