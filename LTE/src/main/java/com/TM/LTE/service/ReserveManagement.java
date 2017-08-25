package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.AirType;
import com.TM.LTE.bean.Member;
import com.TM.LTE.bean.Passenger;
import com.TM.LTE.bean.ProdAir;
import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ReserveAir;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.dao.MemberDao;
import com.TM.LTE.dao.ReserveDao;
@Service
public class ReserveManagement {
	private ModelAndView mav;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private ReserveDao rDao;
	@Autowired
	private MemberDao mDao;
	
	public ModelAndView executeAir(int i) {
		switch (i) {
		case 1:
			lookingForReach();
			break;
		case 2:
			addPassenger();
			break;
		case 3:
			payAir();
			break;
		case 4:
			seatSelect();
			break;
		default:
			break;
		}
		return mav;
	}
 	private void lookingForReach() {
		mav = new ModelAndView();
		ProdAir pa = new ProdAir();
		//pa = rDao.getProductInfo(req.getParameter("prodnum"));
		//mb = mDao.getMemberInfo(session.getAttribute("id").toString());
		//mav.addObject("prodAir", pa);
		//mav.addObject("mbInfo", mb);
		mav.setViewName("airreserve");
	}
	
	private void addPassenger(){
		mav = new ModelAndView();
		Passenger pass;
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		for(int i=1;i<=cnt;i++){
			pass = new Passenger();
			pass.setPs_mid(session.getAttribute("id").toString());
			pass.setPs_separate(Integer.parseInt(req.getParameter("part"+i)));
			pass.setPs_gender(req.getParameter("gender"+i));
			pass.setPs_engfname(req.getParameter("engfname"+i));
			pass.setPs_englname(req.getParameter("englname"+i));
			pass.setPs_korname(req.getParameter("korname"+i));
			pass.setPs_birth(req.getParameter("birth"+i));
			if(rDao.addPassenger(pass)==0){
				System.out.println("탑승객 추가 실패");
			}
			else{
				System.out.println("탑승객 추가 성공");
			}
		}
		mav.setViewName("forward:airReserve");
	}
	
	private void payAir() {
		mav = new ModelAndView();
		ReserveAir ra;
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		int price=0;
		String mnum = null;
		for(int i=1;i<=cnt;i++){
			ra = new ReserveAir();
			ra.setRa_anum(req.getParameter("prodnum"));
			ra.setRa_id(session.getAttribute("id").toString());
			ra.setRa_seatnum(Integer.parseInt(req.getParameter("row"+i)+req.getParameter("col"+i)));
			ra.setRa_name(req.getParameter("raname"));
			price = rDao.priceCalc(req.getParameter("grade"+i));
			ra.setRa_totalprice(price*cnt);
			ra.setRa_grade(req.getParameter("grade"+i));
			mnum = rDao.getPassenger(ra.getRa_id());
			ra.setRa_mnum(mnum);
			if(rDao.airReserve(ra)==0){
				System.out.println("탑승객 추가 실패");
			}
			else{
				System.out.println("탑승객 추가 성공");
			}
		}
		mav.setViewName("forward:airReserve");
	}

	private void seatSelect() {
		mav = new ModelAndView();
		AirType at = new AirType();
		/*at.setAt_prodnum(req.getParameter("prodnum"));
		at.setAt_grade(req.getParameter("grade"));
		at = rDao.selectSeats(at);*/
		mav.addObject("seats", at);
		mav.setViewName("airreserve");
	}

	public ModelAndView executeHotel(int i) {
		switch (i) {
		case 1:
			lookingForRoom();
			break;
		case 2:
			reserveTheRoom();
			break;
		default:
			break;
		}
		return mav;
	}
	
	private void lookingForRoom() {
		mav = new ModelAndView();
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
		sb.append("<div class='ulwrapper'><ul id='index'><li>객실번호</li><li>객실명</li><li>인원수</li><li>가격</li><li>예약하기</li></ul></div>");
		for(int i=0; i<rvList.size(); i++)
		{
			ProdRoom pr = rvList.get(i);
			sb.append("<div class='ulwrapper'><form action='hotelReserve' method='post'>");
			sb.append("<ul class='contents'><li>"+pr.getHtr_rnum()+"<input type='hidden' name='htrrnum' value='"+pr.getHtr_rnum()+"'/>"+"</li>");
			sb.append("<li>"+pr.getHtr_name()+"<input type='hidden' name='htrname' value='"+pr.getHtr_name()+"'/>"+"</li>");
			sb.append("<li>"+pr.getHtr_pnum()+"<input type='hidden' name='htrpnum' value='"+pr.getHtr_pnum()+"'/>"+"</li>");
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
		mav = new ModelAndView();
		ReserveHotel rh = new ReserveHotel();
		rh.setRh_htmid(req.getParameter("htrhtmid"));
		String htmid = rh.getRh_htmid();
		rh.setRh_mid(session.getAttribute("id").toString());
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
			String rhList = makeBookedRoom(session.getAttribute("id").toString());
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
	public ModelAndView executeTicket(int i) {
		switch (i) {
		case 1:
			buyTicket();
			break;
		default:
			break;
		}
		return mav;
	}
	
	private void buyTicket() {
		mav = new ModelAndView();
		ReserveTicket rt = new ReserveTicket();
		rt.setRt_qty(Integer.parseInt(req.getParameter("qty")));
		if(rDao.countRtnum()==0){
			rt.setRt_num(1);
		}else{
			rt.setRt_num(rDao.maxRtnum()+1);
		}
		rt.setRt_mnid(session.getAttribute("id").toString());
		int adultc = Integer.parseInt(req.getParameter("adultc"));
		int childc = Integer.parseInt(req.getParameter("childc"));
		int adultP = adultc * Integer.parseInt(req.getParameter("adultP"));
		int childP = childc * Integer.parseInt(req.getParameter("childP"));
		rt.setRt_total_price(adultP+childP);
		rt.setRt_tnum(req.getParameter("prodnum"));
		rt.setRt_state("구매 완료");
		rDao.insertPayTicket(rt);
		mav.addObject("adultcnt",adultc);
		mav.addObject("childcnt",childc);
		mav.addObject("reserveTcat",rt);
		mav.setViewName("payconfirm");
	}
	
}
