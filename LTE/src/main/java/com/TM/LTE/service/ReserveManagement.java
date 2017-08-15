package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.http.HttpRequest;
>>>>>>> 5c1e419fed37db59aa4ed7d87b59cf5af8460c0a
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.bean.RoomView;
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
		RoomView rv = new RoomView();
		String[] checkIn = req.getParameter("checkIn").split("-");
		int inDate = Integer.parseInt(checkIn[0]+checkIn[1]+checkIn[2]);
		String[] checkOut = req.getParameter("checkOut").split("-");
		int outDate = Integer.parseInt(checkOut[0]+checkOut[1]+checkOut[2]);
		rv.setCheckIn(inDate);
		rv.setCheckOut(outDate);
		rv.setHtr_pnum(Integer.parseInt(req.getParameter("rooms")));
		List<ProdRoom> rvList = rDao.checkLeftRoom(rv);
		String lrList = makeLeftRoom(rvList, inDate, outDate);
		mav.addObject("lrTable", lrList);
		mav.setViewName("hoteldetail");
	}

	private String makeLeftRoom(List<ProdRoom> rvList, int inDate, int outDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<div><ul><li>객실명</li><li>인원수</li><li>가격</li><li>예약하기</li></ul></div>");
		for(int i=0; i<rvList.size(); i++)
		{
			ProdRoom pr = rvList.get(i);
			sb.append("<div><form action='hotelReserve' method='post'>");
			sb.append("<ul><li>"+pr.getHtr_name()+"<input type='hidden' name='htrname' value='"+pr.getHtr_name()+"'/>"+"</li>");
			sb.append("<li name=''>"+pr.getHtr_pnum()+"<input type='hidden' name='htrpnum' value='"+pr.getHtr_pnum()+"'/>"+"</li>");
			sb.append("<li>"+pr.getHtr_price()+"<input type='hidden' name='htrprice' value='"+pr.getHtr_price()+"'/>"+"</li>");
			sb.append("<input type='hidden' name='htrhtmid' value='"+pr.getHtr_htmid()+"'/>");
			sb.append("<input type='hidden' name='htrrnum' value='"+pr.getHtr_rnum()+"'/>");
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
		rh.setRh_mid(ss.getAttribute("id").toString());
		rh.setRh_checkin(req.getParameter("inDate"));
		rh.setRh_checkout(req.getParameter("outDate"));
		rh.setRh_htkrname(req.getParameter(""));
		rh.setRh_htegname(req.getParameter(""));
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
		List<ReserveHotel> sRoom = rDao.selectedRoom(id);
		ReserveHotel rh = sRoom.get(0);
		sb.append("<table><tr><th>객실명</th><th>가격</th><th>체크인</th><th>체크아웃</th>");
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
		//rDao.insertPayTicket(rt);
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
