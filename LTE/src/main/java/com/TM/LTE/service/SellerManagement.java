package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.dao.SellerDao;

@Service
public class SellerManagement {
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private SellerDao sDao;
	
	
	
	public ModelAndView execute(int i) {
		switch(i){
		case 1:
			getSellerList(); break;
		case 2:
			getBestProdUpdate(); break;
		}
		return mav;
	}

	private void getBestProdUpdate() {
		String part = req.getParameter("part");
		String ht_mid = req.getParameter("ht_mid");
		int t_num = Integer.valueOf(req.getParameter("t_num"));
		int result = 0;
		System.out.println(ht_mid);
		System.out.println(t_num);
		if(part.equals("숙박")){
			result = sDao.BestUpdateH(ht_mid);
		}else{
			result = sDao.BestUpdateT(t_num);
		}
		if(result == 0){
			System.out.println("수정실패");
		}else{
			System.out.println("수정성공");
		}
	}

	private void getSellerList() {
		//String val = req.getParameter("testvalue");	
		//System.out.println(val);
		//String id = session.getAttribute("id").toString();
		String id = "AAA";
		System.out.println(id);
		String part = "숙박";//sDao.selectPart(id);
		System.out.println(part);
		int prod  = sDao.selectProd(id);
		System.out.println(prod);
		
		System.out.println(id);
		mav = new ModelAndView();
		List<ProdHotel> hList = null;
		List<ProdTicket> tList = null;
		List<ReserveHotel> rhList = null;
		List<ReserveTicket> rtList = null;
		String sellerhtml = null;
		String memberhtml = null;
		if(part.equals("숙박")){
			hList = sDao.getHotelProdList(id);
			rhList = sDao.getHotelReserveList(id);
			sellerhtml = hlist_html(hList);
			memberhtml = rhList_html(rhList);
		}else{
			tList = sDao.getTicketProdList(id);
			rtList = sDao.getTicketReserveList(prod);
			sellerhtml = tlist_html(tList);
			memberhtml = rtList_html(rtList);
		}
		mav.addObject("sellerList", sellerhtml);
		mav.addObject("memberList", memberhtml);
		mav.addObject("part", part);
		mav.setViewName("sellerpage");
	}
	
	private String rhList_html(List<ReserveHotel> rhList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>호텔명(한글)</td><td>호텔명(영어)</td><td>국가명</td><td>도시명</td><td>연락처</td><td>주소</td><td>구분</td></tr>");
		for(int i=0; i<rhList.size(); i++){
			ReserveHotel rh = rhList.get(i);
			sb.append("<tr><td>"+rh.getRh_num()+"</td>");
			sb.append("<td>"+rh.getRh_mid()+"</td>");
			sb.append("<td>"+rh.getRh_checkin()+"</td>");
			sb.append("<td>"+rh.getRh_checkout()+"</td>");
			sb.append("<td>"+rh.getRh_htkrname()+"</td>");
			sb.append("<td>"+rh.getRh_htrname()+"</td>");
			sb.append("<td>"+rh.getRh_state()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	private String rtList_html(List<ReserveTicket> rtList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>예약번호</td><td>상품번호</td><td>회원아이디</td><td>구매수량</td><td>예약/구매 날짜</td><td>총금액</td><td>사용가능여부</td></tr>");
		for(int i=0; i<rtList.size(); i++){
			ReserveTicket rt = rtList.get(i);
			sb.append("<tr><td>"+rt.getRt_num()+"</td>");
			sb.append("<td>"+rt.getRt_tnum()+"</td>");
			sb.append("<td>"+rt.getRt_mnid()+"</td>");
			sb.append("<td>"+rt.getRt_qty()+"</td>");
			sb.append("<td>"+rt.getRt_date()+"</td>");
			sb.append("<td>"+rt.getRt_total_price()+"</td>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private String hlist_html(List<ProdHotel> hList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>호텔명(한글)</td><td>호텔명(영어)</td><td>국가명</td><td>도시명</td><td>연락처</td><td>주소</td></tr>");
		for(int i=0; i<hList.size(); i++){
			ProdHotel h = hList.get(i);
			sb.append("<tr><td>"+h.getHt_krname()+"</td>");
			sb.append("<td>"+h.getHt_egname()+"</td>");
			sb.append("<td>"+h.getHt_nation()+"</td>");
			sb.append("<td>"+h.getHt_city()+"</td>");
			sb.append("<td>"+h.getHt_msphone()+"</td>");
			sb.append("<td>"+h.getHt_addr()+"</td>");
			sb.append("<td><a href='/bestProd?ht_mid="+ h.getHt_mid() +"'><input type='button' value='추천호텔등록'/></a></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	private String tlist_html(List<ProdTicket> tList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table><tr><td>상품번호</td><td>국가명</td><td>도시명</td><td>상품유형</td><td>상품명</td><td>성인가격</td><td>소인가격</td><td>사용기간</td><td>재고량</td></tr>");
		for(int i=0; i<tList.size(); i++){
			ProdTicket t = tList.get(i);
			sb.append("<tr><td>"+t.getT_num()+"</td>");
			sb.append("<td>"+t.getT_nation()+"</td>");
			sb.append("<td>"+t.getT_city()+"</td>");
			sb.append("<td>"+t.getT_type()+"</td>");
			sb.append("<td>"+t.getT_name()+"</td>");
			sb.append("<td>"+t.getT_aprice()+"</td>");
			sb.append("<td>"+t.getT_cprice()+"</td>");
			sb.append("<td>"+t.getT_start_date()+" ~ "+t.getT_end_date()+"</td>");
			sb.append("<td>"+t.getT_stock()+"</td>");
			sb.append("<td><a href='/bestProd?t_num="+ t.getT_num() + "'><input type='button' value='인기상품등록'/></a></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

}
