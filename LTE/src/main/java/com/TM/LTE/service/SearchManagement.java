package com.TM.LTE.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.TM.LTE.bean.Image;
import com.TM.LTE.bean.SearchImage;
import com.TM.LTE.userClass.Histogram;
import com.TM.LTE.userClass.UploadFile;
import com.TM.LTE.dao.SearchDao;

@Service()

public class SearchManagement {
	
	private ModelAndView mav;
	
	@Autowired
	private SearchDao SDao;
	
	public ModelAndView execute(int code) {
		switch(code){
	      case 1:
	         getSearchContents(); break;
	      default:break;
	      }
	      return mav;
	}
	
	public ModelAndView execute(MultipartHttpServletRequest multi, int code) {
		switch(code){
	    case 1:
	    	imagesearch(multi); break;
	    default:break;
	    }
	    return mav;
	}

	private void getSearchContents() {
		System.out.println("/contents");
		StringBuilder sb = new StringBuilder();
		sb.append("<div id='searchpage'><form action='imagesearch' method='post' "
				+ "enctype='multipart/form-data'><input type='file' name='image'/>"
			+"<button>검색</button></form></div>");
		mav.addObject("searchFrm",sb.toString());
	}

	private void imagesearch(MultipartHttpServletRequest multi) {
		String root=multi.getSession().getServletContext().getRealPath("\\");
		
		Map<String, String> fMap = null;
		UploadFile upload = new UploadFile();  //카페에 등록
			//서버에 파일을 업로드 한뒤,
			//오리지널 파일명, 시스템파일명을 리턴후 맵에 저장
		fMap = upload.fileUp(multi);
		//DB에 저장
		List<Image> ilist = SDao.getimage();
		String airlist = airmakeimage(ilist,root,fMap);
		if(airlist!=null){
			mav.addObject("airlist", airlist);
		}else{
			mav.addObject("airlist", "정보를 찾을 수 없습니다.");
		}
		String hotellist = hotelmakeimage(ilist,root,fMap);
		if(hotellist!=null){
			mav.addObject("hotellist", hotellist);
		}else{
			mav.addObject("hotellist", "정보를 찾을 수 없습니다.");
		}
		String ticketlist = ticketmakeimage(ilist,root,fMap);
		if(ticketlist!=null){
			mav.addObject("ticketlist", ticketlist);
		}else{
			mav.addObject("ticketlist", "정보를 찾을 수 없습니다.");
		}
   		mav.setViewName("main");
	}
	
	private String airmakeimage(List<Image> ilist, String root, Map<String, String> fMap){
	   	String path=root+"\\resources\\upload\\";
	   	StringBuilder sb = new StringBuilder();
	   	sb.append("<span id='title'>항공상품</span>");
	    sb.append("<table id='table' boarder='1'>");
		for(int i=0; i<ilist.size(); i++){
			Image im=ilist.get(i);
			System.out.println(im.getPi_sysname());
			String filename1 = path+im.getPi_sysname();
			String filename2 = path+fMap.get("sysFileName");
			Histogram mn=new Histogram();
			int c = mn.compareHistogram(filename1, filename2);
			if(c==1){
				if(im.getPi_detail()==0 && im.getPi_part()==0){
					List <SearchImage> airlist = SDao.airselect(im.getPi_num());
					sb.append("<tr><td><img src='resources/upload" +"/" + im.getPi_sysname() + "' width = '150' height = '150'></td></tr>");
					for(int k=0; k<airlist.size(); k++){
						SearchImage si=airlist.get(i);
						sb.append("<tr><td>"+si.getAir_nation()+"-"+si.getAir_city()+"</td></tr>");
					}
				}
			}
		}
		sb.append("</table>");
		return sb.toString();
   }
	
	private String hotelmakeimage(List<Image> ilist, String root, Map<String, String> fMap) {
		String path=root+"\\resources\\upload\\";
		StringBuilder sb = new StringBuilder();
		sb.append("<span id='title'>항공상품</span>");
		sb.append("<table id='table' boarder='1'>");
		for(int i=0; i<ilist.size(); i++){
			Image im=ilist.get(i);
			System.out.println(im.getPi_sysname());
			String filename1 = path+im.getPi_sysname();
			String filename2 = path+fMap.get("sysFileName");
			Histogram mn=new Histogram();
			int c = mn.compareHistogram(filename1, filename2);
			if(c==1){
				if(im.getPi_detail()==0 && im.getPi_part()==0){
					List <SearchImage> hotellist = SDao.hotelselect(im.getPi_num());
					sb.append("<tr><td><img src='resources/upload" +"/" + im.getPi_sysname() + "' width = '150' height = '150'></td></tr>");
					for(int k=0; k<hotellist.size(); k++){
						SearchImage si=hotellist.get(i);
						sb.append("<tr><td>"+si.getHt_nation()+"-"+si.getHt_city()+"-"+si.getHt_krname()+"</td></tr>");
					}
				}
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private String ticketmakeimage(List<Image> ilist, String root, Map<String, String> fMap) {
		String path=root+"\\resources\\upload\\";
	   	StringBuilder sb = new StringBuilder();
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
				if(im.getPi_detail()==0 && im.getPi_part()==0){
					List <SearchImage> ticketlist = SDao.ticketselect(im.getPi_num());
					sb.append("<tr><td><img src='resources/upload" +"/" + im.getPi_sysname() + "' width = '150' height = '150'></td></tr>");
					for(int k=0; k<ticketlist.size(); k++){
						SearchImage si=ticketlist.get(i);
						sb.append("<tr><td>"+si.getT_nation()+"-"+si.getT_city()+"-"+si.getT_name()+"</td></tr>");
					}
				}
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
}
