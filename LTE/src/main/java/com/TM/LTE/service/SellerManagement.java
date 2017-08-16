package com.TM.LTE.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Image;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.dao.SellerDao;
import com.TM.LTE.userClass.UploadFile1;

@Service
public class SellerManagement {
	private ModelAndView mav;

	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private SellerDao sDao;

	public ModelAndView execute(MultipartHttpServletRequest multi, int cmd) {
		switch(cmd){
		case 1:
			hotelWrite(multi);break;
		case 2:
			
		default:
			break;
		}
		return mav;
	}

	private void hotelWrite(MultipartHttpServletRequest multi) {
		String id = "HBB";//session.getAttribute("id").toString();
		String krname = multi.getParameter("krname");
		String egname = multi.getParameter("egname");
		String nationSel = multi.getParameter("nationSel");
		String citySel = multi.getParameter("citySel");
		String phonefirst = multi.getParameter("phonefirst");
		String phonecenter = multi.getParameter("phonecenter");
		String phonelast = multi.getParameter("phonelast");
		String addr = multi.getParameter("addr");
		ProdHotel prodhotel = new ProdHotel();
		int count = Integer.valueOf(multi.getParameter("count"));
		for(int i=0; i < count+1; i++){
			String name = multi.getParameter("r_name"+i);
			int num = Integer.valueOf(multi.getParameter("r_num"+i));
			String price = multi.getParameter("r_price"+i);
			int person = Integer.valueOf(multi.getParameter("r_person"+i));
			prodhotel.setHt_mid(id);
			prodhotel.setHtr_rnum(num);
			prodhotel.setHtr_name(name);
			prodhotel.setHtr_price(price);
			prodhotel.setHtr_pnum(person);
			if(sDao.insertRoom(prodhotel) == 1){
				System.out.println("insert성공");
			}else{
				System.out.println("insert실패");
			}
		}

		int check1 = Integer.parseInt(multi.getParameter("fileCheck1"));
		int check2 = Integer.parseInt(multi.getParameter("fileCheck2"));
		int check3 = Integer.parseInt(multi.getParameter("fileCheck3"));
		LinkedHashMap<String, String> fMap = null;
		

		prodhotel.setHt_mid(id);
		prodhotel.setHt_krname(krname);
		prodhotel.setHt_egname(egname);
		prodhotel.setHt_nation(nationSel);
		prodhotel.setHt_city(citySel);
		prodhotel.setHt_msphone(phonefirst+phonecenter+phonelast);
		prodhotel.setHt_addr(addr);

		sDao.insertWrite(prodhotel);
		String hnum = sDao.hotelNum(id);
		System.out.println(hnum);
		String detail = "숙박";
		if(check1==1 && check2 == 1 && check3 ==1){
			UploadFile1 upload=new UploadFile1();
			fMap = upload.fileUp(multi, hnum, detail);
		}
		Image im = new Image();
		
		for(int i =0; i < 3; i++){
			im.setPi_oriname(fMap.get("oriFileName"+i));
			im.setPi_sysname(fMap.get("sysFileName"+i));
			im.setPi_path(fMap.get("path"+i));
			im.setPi_num(fMap.get("hnum"+i));
			im.setPi_detail(fMap.get("detail"+i));
			sDao.fileInsert(im);
		}
		mav = new ModelAndView();
		mav.addObject("hnum", hnum);
		mav.setViewName("proddetail");
	}

	public ModelAndView execute(int i) {
		switch(i){
		case 1:
			getSellerList(); break;
		case 2:
			getBestProdUpdate(); break;
		case 3:
			hotelUpdateFrm(); break;
		}
		return mav;
	}
	private void hotelUpdateFrm() {
		String hnum = req.getParameter("hnum");
		ProdHotel ph = new ProdHotel();
		List<Image> imList = null;
		
		ph = sDao.selectUpFrm(hnum);
		imList = sDao.selectName(hnum);
		System.out.println(ph.getHt_city());
		String updatehtml = make_html(ph, imList);
		mav = new ModelAndView();
		mav.addObject("updatehtml", updatehtml);
		mav.setViewName("hotelupdate");
	}
	private String make_html(ProdHotel ph, List<Image> imList) {
		StringBuilder sb=new StringBuilder();
		int i = 0;
		Image im = imList.get(i);
		sb.append("<img src='./resources/hotel/"+ im.getPi_sysname() +"'width='100px'/>");
		sb.append("<div>");
		sb.append("<p name='nation'>국가명  : "+ph.getHt_nation()+"</p>");
		sb.append("<p name='city'>도시명  : "+ph.getHt_city()+"</p>");
		sb.append("호텔명(한국) <input type='text' value='"+ph.getHt_krname()+"' name='krname'/><br/>");
		sb.append("호텔명(영어) <input type='text' value='"+ph.getHt_egname()+"' name='egname'/><br/>");
		sb.append("연락처  : <input type='text' value='"+ph.getHt_msphone()+"' name='msphone'/><br/>");
		sb.append("주소  : <input type='text' value='"+ph.getHt_addr()+"' name='addr'/><br/>");
		im = imList.get(1);
		sb.append("<img src='./resources/hotel/"+ im.getPi_sysname() +"'width='100px'/>");
		im = imList.get(2);
		sb.append("<img src='./resources/hotel/"+ im.getPi_sysname() +"'width='100px'/>");
		sb.append("</div>");
		return sb.toString();
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
