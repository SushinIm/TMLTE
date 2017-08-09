package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdTicket;
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
		}
		return mav;
	}

	private void getSellerList() {
		//String id = session.getAttribute("id").toString();
		String part = "숙박";//sDao.selectPart(id);
		System.out.println(part);
		String id = "AAA";
		System.out.println(id);
		mav = new ModelAndView();
		List<ProdHotel> hList = null;
		List<ProdTicket> tList = null;
		String sellerhtml = null;
		if(part.equals("숙박")){
			hList = sDao.getHotelProdList(id);
			sellerhtml = hlist_html(hList);
		}else{
			tList = sDao.getTicketProdList(id);
			sellerhtml = tlist_html(tList);
		}
		mav.addObject("sellerList", sellerhtml);
		mav.setViewName("sellerpage");
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
			sb.append("<td>"+h.getHt_addr()+"</td></tr>");
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
			sb.append("<td>"+t.getT_stock()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

}
