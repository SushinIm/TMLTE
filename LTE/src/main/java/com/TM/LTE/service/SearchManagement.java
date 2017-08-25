package com.TM.LTE.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.TM.LTE.bean.Image;
import com.TM.LTE.bean.Member;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.SearchImage;
import com.TM.LTE.userClass.Histogram;
import com.TM.LTE.userClass.UploadFile;
import com.google.gson.Gson;
import com.TM.LTE.dao.SearchDao;

@Service
public class SearchManagement {
	
	private ModelAndView mav;
	
	@Autowired
	private SearchDao sDao;
	
	@Autowired
	HttpServletRequest req;
	
	private String json;
	
	public ModelAndView execute(MultipartHttpServletRequest multi, int code) {
		switch(code){
	    case 1:
	    	ImageSearch(multi); break;
	    default:break;
	    }
	    return mav;
	}
	
	public String execute(int code) {
		switch(code){
		case 1:
			hotelsearch(); break; //호텔검색
		case 2:
			ticketsearch(); break; //티켓검색
		default:break;
	    }
	    return json;
	}
	
	 private void ticketsearch() {
			String nation=req.getParameter("nation");
			String city=req.getParameter("city");
			String type=req.getParameter("type");
			String tname=req.getParameter("tname");
			ProdTicket pt=new ProdTicket();
			pt.setT_nation(nation);
			pt.setT_city(city);
			String tslist=null;
			if(type==""&&tname==""){
				List<ProdTicket>ptlist=sDao.ticketsearch1(pt);
				tslist=maketsList(ptlist);
			}else if(tname==""){
				pt.setT_type(type);
				List<ProdTicket>ptlist=sDao.ticketsearch2(pt);
				tslist=maketsList(ptlist);
			}else{
				pt.setT_type(type);
				pt.setT_name(tname);
				List<ProdTicket>ptlist=sDao.ticketsearch3(pt);
				tslist=maketsList(ptlist);
			}
			Gson jsonObj=new Gson();
			json=jsonObj.toJson(tslist);
	 }

	 private String maketsList(List<ProdTicket> ptlist) {
		 StringBuilder sb=new StringBuilder();
			if(ptlist.size()==0){
				sb.append("<table style='text-align:center;'><tr><td>검색결과가 없습니다.</td></tr></table>");
			}else{
				for(int i=0;i<ptlist.size();i++){
					ProdTicket pt=ptlist.get(i);
					//Image im = sDao.imageselectnum(pt.getT_num());
					sb.append("<div><table>");
					/*sb.append("<tr><td><img src='resources/upload" +"/" + im.getPi_sysname() 
					+ "' width = '150' height = '150'></td></tr>");*/
					sb.append("<tr><td>"+pt.getT_name()+"</td></tr>");
					sb.append("<tr><td>"+pt.getT_nation()+">"+pt.getT_city()+"</td></tr>");
					sb.append("<tr><td>"+pt.getT_cprice()+"원~"+"</td></tr>");
					sb.append("</table></div>");
				}
			}
			return sb.toString();
	}

	private void hotelsearch() {
			String nation=req.getParameter("nation");
			String city=req.getParameter("city");
			String checkin=req.getParameter("checkin");
			String checkout=req.getParameter("checkout");
			String hname=req.getParameter("hname");
			ReserveHotel rh=new ReserveHotel();
			rh.setRh_nation(nation);
			rh.setRh_city(city);
			rh.setRh_checkin(checkin);
			rh.setRh_checkout(checkout);
			String hslist=null;
			if(hname==""){
				List<ProdHotel>phlist=sDao.hotelsearch1(rh);
				hslist=makehsList(phlist);
			}else{
				rh.setRh_htrname(hname);
				List<ProdHotel>phlist=sDao.hotelsearch2(rh);
				hslist=makehsList(phlist);
			}
			Gson jsonObj=new Gson();
			json=jsonObj.toJson(hslist);
	   }
	 
	private String makehsList(List<ProdHotel> phlist) {
		StringBuilder sb=new StringBuilder();
		if(phlist.size()==0){
			sb.append("<table style='text-align:center;'><tr><td>검색결과가 없습니다.</td></tr></table>");
		}else{
			for(int i=0;i<phlist.size();i++){
				ProdHotel ph=phlist.get(i);
				//Image im = sDao.imageselectnum(ph.getHt_num());
				ProdRoom phr = sDao.hotelselectprice(ph.getHt_mid());
				sb.append("<table>");
				sb.append("<tr><td rowspan='3'>asdasd</td><td>영문명 : "+ph.getHt_egname()+"</td></tr>");
				/*sb.append("<tr><td rowspan='3'><img src='resources/upload" +"/" + im.getPi_sysname() 
				+ "' width = '150' height = '150'></td><td>영문명 : "+ph.getHt_egname()+"</td></tr>");*/
				sb.append("<tr><td>위치 : "+ph.getHt_nation()+">"+ph.getHt_city()+"</td></tr>");
				sb.append("<tr><td>주소 : "+ph.getHt_addr()+"</td></tr>");
				sb.append("<tr><td>"+ph.getHt_krname()+"</td><td>"+phr.getHtr_price()+"</td></tr>");
				sb.append("</table>");
			}
		}
		return sb.toString();
	}

	private void ImageSearch(MultipartHttpServletRequest multi) {
		String root=multi.getSession().getServletContext().getRealPath("\\");
		Map<String, String> fMap = null;
		UploadFile upload = new UploadFile();  
		fMap = upload.fileUp(multi);
		List<Image> ilist = sDao.getimage();
		String airlist = airmakeList(ilist,root,fMap);
		mav.addObject("airlist", airlist);
		String hotellist = hotelmakeList(ilist,root,fMap);
		mav.addObject("hotellist", hotellist);
		String ticketlist = ticketmakeList(ilist,root,fMap);
		mav.addObject("ticketlist", ticketlist);
   		mav.setViewName("main");
	}
	
	private String airmakeList(List<Image> ilist, String root, Map<String, String> fMap){
	   	String path=root+"\\resources\\upload\\";
	   	StringBuilder sb = new StringBuilder();
	   	int cnt = 0;
	   	sb.append("<span id='title'>항공상품</span>");
	    sb.append("<table id='table' boarder='1'>");
		for(int i=0; i<ilist.size(); i++){
			Image im=ilist.get(i);
			String filename1 = path+im.getPi_sysname();
			String filename2 = path+fMap.get("sysFileName");
			Histogram mn=new Histogram();
			int c = mn.compareHistogram(filename1, filename2);
			if(c==1){
				if(im.getPi_detail().equals("항공") && im.getPi_part()==0){
					sb.append("<tr><td><img src='resources/upload" +"/" + im.getPi_sysname() 
					+ "' width = '150' height = '150'></td></tr>");
					SearchImage si = sDao.airselect(im.getPi_num());
					sb.append("<tr><td>"+si.getAir_nation()+"-"+si.getAir_city()+"</td></tr>");
				}
			}else if(c==0){
				cnt++;
			}
		}
		if(cnt==ilist.size()){
			sb.append("<div>검색결과가 없습니다.</div>");
		}
		sb.append("</table>");
		return sb.toString();
    }
	
	private String hotelmakeList(List<Image> ilist, String root, Map<String, String> fMap) {
		String path=root+"\\resources\\upload\\";
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		sb.append("<span id='title'>호텔상품</span>");
		sb.append("<table id='table' boarder='1'>");
		for(int i=0; i<ilist.size(); i++){
			Image im=ilist.get(i);
			System.out.println(im.getPi_sysname());
			String filename1 = path+im.getPi_sysname();
			String filename2 = path+fMap.get("sysFileName");
			Histogram mn=new Histogram();
			int c = mn.compareHistogram(filename1, filename2);
			if(c==1){
				if(im.getPi_detail().equals("숙박") && im.getPi_part()==0){
					sb.append("<tr><td><img src='resources/upload" +"/" + im.getPi_sysname() 
					+ "' width = '150' height = '150'></td></tr>");
					SearchImage si = sDao.hotelselect(im.getPi_num());
					sb.append("<tr><td>"+si.getHt_nation()+"-"+si.getHt_city()+"-"+si.getHt_krname()+"</td></tr>");
					}
			}else if(c==0){
				cnt++;
			}
		}
		if(cnt==ilist.size()){
			sb.append("<div>검색결과가 없습니다.</div>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private String ticketmakeList(List<Image> ilist, String root, Map<String, String> fMap) {
		String path=root+"\\resources\\upload\\";
	   	StringBuilder sb = new StringBuilder();
		int cnt = 0;
	   	sb.append("<span id='title'>티켓상품</span>");
	    sb.append("<table id='table' boarder='1'>");
		for(int i=0; i<ilist.size(); i++){
			Image im=ilist.get(i);
			System.out.println(im.getPi_sysname());
			String filename1 = path+im.getPi_sysname();
			String filename2 = path+fMap.get("sysFileName");
			Histogram mn=new Histogram();
			int c = mn.compareHistogram(filename1, filename2);
			if(c==1){
				if(im.getPi_detail().equals("레저/입장권") && im.getPi_part()==0){
					sb.append("<tr><td><img src='resources/upload" +"/" + im.getPi_sysname() 
					+ "' width = '150' height = '150'></td></tr>");
					SearchImage si = sDao.ticketselect(im.getPi_num());
					sb.append("<tr><td>"+si.getT_nation()+"-"+si.getT_city()+"-"+si.getT_name()+"</td></tr>");
					}
			}else if(c==0){
				cnt++;
			}
		}
		if(cnt==ilist.size()){
			sb.append("<div>검색결과가 없습니다.</div>");
		}
		sb.append("</table>");
		return sb.toString();
	}
}
