package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.dao.ReserveDao;
@Service
public class ReserveManagement {
	private ModelAndView mav;
	@Autowired
	private HttpSession ss;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private ReserveDao rDao;
	
	public ModelAndView execute(int i) {
		switch (i) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			lookingForRoom();
			break;
		case 8:
			reserveTheRoom();
			break;
		case 9:
			buyTicket();
			break;
		default:
			break;
		}
		return mav;
	}

	private void lookingForRoom() {
		ReserveHotel rh = new ReserveHotel();
		String[] checkIn = req.getParameter("checkIn").split("-");
		String inDate = checkIn[0]+checkIn[1]+checkIn[2];
		String[] checkOut = req.getParameter("checkOut").split("-");
		String outDate = checkOut[0]+checkOut[1]+checkOut[2];
		rh.setRh_checkin(inDate);
		rh.setRh_checkout(outDate);
		rh.setRh_htkrname(req.getParameter("htkrname"));
		rh.setHtr_pnum(Integer.parseInt(req.getParameter("rooms")));
		List<ProdRoom> rvList = rDao.checkLeftRoom(rh);
		System.out.println(rvList);
		String lrList = makeLeftRoom(rvList, inDate, outDate);
		System.out.println(lrList);
		mav.addObject("lrTable", lrList);
		mav.setViewName("hoteldetail");
	}

	private String makeLeftRoom(List<ProdRoom> rvList, String inDate, String outDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<div><ul><li>객실명</li><li>인원수</li><li>가격</li><li>예약하기</li></ul></div>");
		for(int i=0; i<rvList.size(); i++)
		{
			ProdRoom pr = rvList.get(i);
			sb.append("<div><form action='hotelReserve' method='post'>");
			sb.append("<ul><li>"+pr.getHtr_rnum()+"<input type='hidden' name='htrrnum' value='"+pr.getHtr_rnum()+"'/>"+"</li>");
			sb.append("<ul><li>"+pr.getHtr_name()+"<input type='hidden' name='htrname' value='"+pr.getHtr_name()+"'/>"+"</li>");
			sb.append("<li name=''>"+pr.getHtr_pnum()+"<input type='hidden' name='htrpnum' value='"+pr.getHtr_pnum()+"'/>"+"</li>");
			sb.append("<li>"+pr.getHtr_price()+"<input type='hidden' name='htrprice' value='"+pr.getHtr_price()+"'/>"+"</li>");
			sb.append("<input type='hidden' name='htrhtmid' value='"+pr.getHtr_htmid()+"'/>");
			sb.append("<input type='hidden' name='inDate' value='"+inDate+"'/>");
			sb.append("<input type='hidden' name='outDate' value='"+outDate+"'/>");
			sb.append("<li><input type='submit' value='실시간 예약'/></li></ul>");
			sb.append("</form></div>");
		}
		sb.append("</div>");
		return sb.toString(); 
	}

	private void reserveTheRoom() {
		ReserveHotel rh = new ReserveHotel();
		rh.setRh_htmid(req.getParameter("htrhtmid"));
		String htmid = rh.getRh_htmid();
		rh.setRh_mid(ss.getAttribute("id").toString());
		rh.setRh_checkin(req.getParameter("inDate"));
		rh.setRh_checkout(req.getParameter("outDate"));
		rh.setRh_htkrname(rDao.gethtkrname(htmid));
		rh.setRh_htegname(rDao.gethtegname(htmid));
		rh.setRh_htrname(req.getParameter("htrname"));
		rh.setRh_rnum(Integer.parseInt(req.getParameter("htrrnum")));
		rh.setRh_price(Integer.parseInt(req.getParameter("htrprice")));
		if(rDao.reserveTheRoom(rh)==0){
			mav.addObject("msg", "예약에 실패했다");
			mav.setViewName("hoteldetail");
		}else{
			String rhList = makeBookedRoom(ss.getAttribute("id").toString());
			mav.addObject("aboutPay", rhList);
			mav.setViewName("payconfirm");
		}
	}

	private String makeBookedRoom(String id) {
		StringBuilder sb = new StringBuilder();
		ReserveHotel rh = rDao.selectedRoom(id);
		sb.append("<table><tr><th>객실명</th><th>가격</th><th>체크인</th><th>체크아웃</th></tr>");
		sb.append("<tr><td>"+rh.getRh_htrname()+"</td>");
		sb.append("<td>"+rh.getRh_price()+"</td>");
		sb.append("<td>"+rh.getRh_checkin()+"</td>");
		sb.append("<td>"+rh.getRh_checkout()+"</td></tr>");
		sb.append("</table>");
		return sb.toString();
	}

	private void buyTicket() {
		ReserveTicket rt = new ReserveTicket();
		rt.setRt_qty(Integer.parseInt(req.getParameter("qty")));
		if(rDao.countRtnum()==0){
			rt.setRt_num(1);
		}else{
			rt.setRt_num(rDao.maxRtnum()+1);
		}
		rt.setRt_mnid(ss.getAttribute("id").toString());
		int adultc = Integer.parseInt(req.getParameter("adultc"));
		int childc = Integer.parseInt(req.getParameter("childc"));
		int adultP = adultc * Integer.parseInt(req.getParameter("adultP"));
		int childP = childc * Integer.parseInt(req.getParameter("childP"));
		rt.setRt_total_price(adultP+childP);
		rt.setRt_tnum(req.getParameter("prodnum"));
		rt.setRt_state("구매 완료");
		rDao.insertPayTicket(rt);
		payTicket(adultc, childc, rt);
	}
	
	private void payTicket(int adultc, int childc, ReserveTicket rt) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table><tr><td>상품번호</td><td>성인 수</td><td>소아 수</td><td>총액</td></tr>");
		sb.append("<tr><td>"+rt.getRt_tnum()+"</td><td>"+adultc+"</td><td>"+childc+"</td><td>"+rt.getRt_total_price()+"</td></tr></table>");
		mav.addObject("aboutPay", sb.toString());
		mav.setViewName("payconfirm");
	}
}
