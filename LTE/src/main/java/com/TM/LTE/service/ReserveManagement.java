package com.TM.LTE.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.dao.ReserveDao;

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
			buyTicket();
			break;
		default:
			break;
		}
		return mav;
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
		mav.setViewName("payticket");
	}
}
