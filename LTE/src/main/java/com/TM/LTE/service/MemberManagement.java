package com.TM.LTE.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Member;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveAir;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.dao.MemberDao;
import com.google.gson.Gson;

@Service
public class MemberManagement {
	   @Autowired //위에 선언함->아래의 new 선언하지 말기!
	   private MemberDao mDao;
	   @Autowired
	   HttpServletRequest req;
	   @Autowired 
	   private HttpSession session; //HttpSession->인터페이스(Bean태그 등록하지 않는다.)
	   @Autowired
	   private HttpServletResponse response;
	   
	   private ModelAndView mav;
	   
	   private String json;
	   
	   public void ReservManagement(HttpServletRequest req, HttpServletResponse response) {
			this.req = req;
			this.response = response;
		}
		
	   public ModelAndView execute(Member mb, int cmd){
	      switch(cmd){
	      case 0:
	    	 main(); break; //메인
	      case 1:
	          memberAccess(mb); break; //로그인
	      case 2:
	          memberInsert(mb); break; //회원가입(일반)
	      case 3:
	          sellerInsert(mb); break; //회원가입(판매자)
	      case 4:
		      logout(mb); break; //로그아웃
	      case 5:
	    	  mypage(mb); break; //마이페이지(일반)
	      case 6:
	    	  mypageseller(mb); break; //마이페이지(판매자)
	      case 7:
	    	  editinfo(mb); break; //정보수정전 비밀번호 확인(일반)
	      case 8:
	    	  editinfoseller(mb); break; //정보수정전 비밀번호 확인(판매자)
	      case 9:
	    	  editinfosave(mb); break; //정보수정(일반)
	      case 10:
	    	  editinfosaveseller(mb); break; //정보수정(판매자)
	      default:
	         break;
	      }
	      return mav;
	   }
	   
	   public String execute(int i) {
		   switch(i){
		   case 1:
			   idcheck(); break; //아이디중복
		   case 2:
			   findid(); break; //아이디찾기
		   case 3:
			   findpw(); break; //비번찾기
		   default:
			   break;
		   }
		   return json;	
	   }
	   
	private void main() {
		if(session.getAttribute("id") == null){
	          mav.addObject("login", null);
	    }else{
	    	  String id = session.getAttribute("id").toString();
	    	  Member mb = mDao.getmemberinfo(id);
	          mav.addObject("login", mDao.getPart(mb));
	          System.out.println("메인");
	       }
	         mav.setViewName("main");
	 }
	   

	private void editinfo(Member mb) { //정보수정전 비밀번호 확인(일반)
		mav=new ModelAndView();
		BCryptPasswordEncoder pwEncoder=new BCryptPasswordEncoder(); //필드에 올려도 됨 
        String pwEncode=mDao.getSecurityPwd(mb.getM_id()); //id로 암호화된 pw를 가져옴
        	if(pwEncoder.matches(mb.getM_pw(), pwEncode)){ //방식이 똑같다면...
        		mb=mDao.getmemberinfo(mb.getM_id()); //비밀번호 일치시 멤버정보 가져오기
				int emailIndex = mb.getM_email().indexOf("@");
				String m_email1 = mb.getM_email().substring(0, emailIndex);
				String m_email2 = mb.getM_email().substring(emailIndex + 1);
				String m_phone1 = mb.getM_phone().substring(0, 3);
				String m_phone2 = mb.getM_phone().substring(4, 8);
				String m_phone3 = mb.getM_phone().substring(9);
				mav.addObject("member", mb);
				mav.addObject("m_email1", m_email1);
				mav.addObject("m_email2", m_email2);
				mav.addObject("m_phone1", m_phone1);
				mav.addObject("m_phone2", m_phone2);
				mav.addObject("m_phone3", m_phone3);
				mav.setViewName("editinfo");
			}else{
				mav.setViewName("editinfobefore");
			}
		}  
	   
	private void editinfoseller(Member mb) { //정보수정전 비밀번호 확인(판매자)
		mav=new ModelAndView();
		BCryptPasswordEncoder pwEncoder=new BCryptPasswordEncoder(); //필드에 올려도 됨 
        String pwEncode=mDao.getSecurityPwd(mb.getM_id()); //id로 암호화된 pw를 가져옴
        	if(pwEncoder.matches(mb.getM_pw(), pwEncode)){ //방식이 똑같다면...
				mb=mDao.getmemberinfo(mb.getM_id()); //비밀번호 일치시 멤버정보 가져오기
				int emailIndex = mb.getM_email().indexOf("@");
				String m_email1 = mb.getM_email().substring(0, emailIndex);
				String m_email2 = mb.getM_email().substring(emailIndex + 1);
				String m_phone1 = mb.getM_phone().substring(0, 3);
				String m_phone2 = mb.getM_phone().substring(4, 8);
				String m_phone3 = mb.getM_phone().substring(9);
				mav.addObject("member", mb);
				mav.addObject("m_email1", m_email1);
				mav.addObject("m_email2", m_email2);
				mav.addObject("m_phone1", m_phone1);
				mav.addObject("m_phone2", m_phone2);
				mav.addObject("m_phone3", m_phone3);
				mav.setViewName("editinfoseller");
			}else{
				mav.setViewName("editinfobeforeseller");
			}
	}
	
	private void mypageseller(Member mb) { //마이페이지(판매자)
		mav=new ModelAndView();
		String id = (String)session.getAttribute("id");
		mb=mDao.mypage(id);
		mav.addObject("member", mb);
		mb=mDao.getmemberpart(id);
		String part=mb.getM_part();
				
		if(part.equals("숙박")){
			List<ReserveHotel> list1 = mDao.getrhotel(id);
			String phlist = makehlist(list1,part);
			mav.addObject("phlist",phlist);
			List<ProdHotel> list2 = mDao.getphotel(id);
			String rhlist = makerhlist(list2,part);
			mav.addObject("rhlist",rhlist);
			List<ProdRoom> list3 = mDao.getphotelroom(id);
			String rhrlist = makerhrlist(list3,part);
			mav.addObject("rhrlist",rhrlist);
		}else if(part.equals("레저/입장권")){
			List<ReserveTicket> list1 = mDao.getrticket(id);
			String ptlist = maketlist(list1,part);
			mav.addObject("ptlist",ptlist);
			List<ProdTicket> list2 = mDao.getpticket(id);
			String rtlist = makertlist(list2,part);
			mav.addObject("rtlist",rtlist);
		}
		mav.setViewName("mypageseller");
	}
	
	private String makerhlist(List<ProdHotel> list2, String part) {
		StringBuilder sb=new StringBuilder();
		sb.append("등록된 숙박상품</br><table>");
		sb.append("<tr><td>상품번호</td><td>호텔명(한글)</td><td>호텔명(영어)</td><td>국가명</td><td>도시명</td><td>주소</td></tr>");
		for(int i=0;i<list2.size();i++){
			ProdHotel ph=list2.get(i);
			sb.append("<tr><td>"+ph.getHt_num()+"</td><td>"+ph.getHt_krname()+"</td><td>"+ph.getHt_egname()+"</td>"
					+ "<td>"+ph.getHt_nation()+"</td><td>"+ph.getHt_city()+"</td><td>"+ph.getHt_addr()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private String makerhrlist(List<ProdRoom> list3, String part) {
		StringBuilder sb=new StringBuilder();
		sb.append("등록된 객실상품</br><table>");
		sb.append("<tr><td>객실번호</td><td>객실명</td><td>객실가격</td><td>기본/최대인원</td></tr>");
		for(int i=0;i<list3.size();i++){
			ProdRoom phr=list3.get(i);
			sb.append("<tr><td>"+phr.getHtr_rnum()+"</td><td>"+phr.getHtr_name()+"</td>"
					+ "<td>"+phr.getHtr_price()+"</td><td>"+phr.getHtr_pnum()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private String makertlist(List<ProdTicket> list2, String part) {
		StringBuilder sb=new StringBuilder();
		sb.append("등록된 티켓상품</br><table>");
		sb.append("<tr><td>상품번호</td><td>국가명</td><td>도시명</td><td>상품유형</td><td>상품명</td><td>성인가격</td><td>소인가격</td>"
				+ "<td>사용시작일</td><td>사용마감일</td><td>재고량</td><td>성인가격</td><td>인기상품</td></tr>");
		for(int i=0;i<list2.size();i++){
			ProdTicket pt=list2.get(i);
			sb.append("<tr><td>"+pt.getT_num()+"</td><td>"+pt.getT_nation()+"</td><td>"+pt.getT_city()+"</td>td>"+pt.getT_type()+"</td>"
					+ "<td>"+pt.getT_name()+"</td><td>"+pt.getT_aprice()+"</td><td>"+pt.getT_cprice()+"</td><td>"+pt.getT_start_date()+"</td>"
							+ "<td>"+pt.getT_end_date()+"</td><td>"+pt.getT_stock()+"</td><td>"+pt.getT_best()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private void mypage(Member mb) { //마이페이지(일반)
		mav=new ModelAndView();
		String id = (String)session.getAttribute("id");
		mb=mDao.mypage(id);
		mav.addObject("member", mb);
		mb=mDao.getmemberpart(id);
		String part=mb.getM_part();
		List<ReserveAir> list1 = mDao.getrair(id);
		String alist = makealist(list1);
		mav.addObject("alist",alist);
		List<ReserveHotel> list2 = mDao.getrhotel(id);
		String hlist = makehlist(list2,part);
		mav.addObject("hlist",hlist);
		List<ReserveTicket> list3 = mDao.getrticket(id);
		String tlist = maketlist(list3,part);
		mav.addObject("tlist",tlist);
		mav.setViewName("mypage");
	}
	
	private String makealist(List<ReserveAir> list) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table>");
		sb.append("<tr><td>상품번호</td><td>예약번호</td><td>좌석번호</td><td>등급</td><td>예약자명</td><td>예약일</td><td>총 결제금액</td></tr>");
		for(int i=0;i<list.size();i++){
			ReserveAir ra=list.get(i);
			sb.append("<tr><td>"+ra.getRa_anum()+"</td><td>"+ra.getRa_num()+"</td><td>"+ra.getRa_seatnum()+"</td>"
					+ "<td>"+ra.getRa_grade()+"</td><td>"+ra.getRa_name()+"</td><td>"+ra.getRa_date()+"</td><td>"+ra.getRa_totalprice()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private String makehlist(List<ReserveHotel> list, String part) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table>");
		if(part.equals("일반")){
			sb.append("<tr><td>예약번호</td><td>체크인</td><td>체크아웃</td><td>호텔명(한글)</td>"
					+ "<td>호텔명(영어)</td><td>객실명</td><td>총 가격</td><td>예약상태</td></tr>");
			for(int i=0;i<list.size();i++){
				ReserveHotel rh=list.get(i);
				sb.append("<tr><td>"+rh.getRh_num()+"</td><td>"+rh.getRh_checkin()+"</td><td>"+rh.getRh_checkout()+"</td><td>"+rh.getRh_htkrname()+"</td>"
						+ "<td>"+rh.getRh_htegname()+"</td><td>"+rh.getRh_htrname()+"</td><td>"+rh.getRh_price()+"</td><td>"+rh.getRh_state()+"</td></tr>");
			}
		}else{
			sb.append("<tr><td>예약번호</td><td>회원아이디</td><td>체크인</td><td>체크아웃</td><td>호텔명(한글)</td>"
					+ "<td>호텔명(영어)</td><td>객실명</td><td>총 가격</td><td>예약상태</td></tr>");
			for(int i=0;i<list.size();i++){
				ReserveHotel rh=list.get(i);
				sb.append("<tr><td>"+rh.getRh_num()+"</td><td>"+rh.getRh_mid()+"</td><td>"+rh.getRh_checkin()+"</td><td>"+rh.getRh_checkout()+"</td><td>"+rh.getRh_htkrname()+"</td>"
						+ "<td>"+rh.getRh_htegname()+"</td><td>"+rh.getRh_htrname()+"</td><td>"+rh.getRh_price()+"</td><td>"+rh.getRh_state()+"</td></tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private String maketlist(List<ReserveTicket> list, String part) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table>");
		if(part.equals("일반")){
			sb.append("<tr><td>상품번호</td><td>예약번호</td><td>구매수량</td><td>예약날짜</td><td>총 금액</td><td>예약상태</td></tr>");
			for(int i=0;i<list.size();i++){
				ReserveTicket rt=list.get(i);
				sb.append("<tr><td>"+rt.getRt_tnum()+"</td><td>"+rt.getRt_num()+"</td><td>"+rt.getRt_qty()+"</td>"
						+ "<td>"+rt.getRt_date()+"</td><td>"+rt.getRt_total_price()+"</td><td>"+rt.getRt_state()+"</td></tr>");
			}
		}else{
			sb.append("<tr><td>상품번호</td><td>예약번호</td><td>회원아이디</td><td>구매수량</td><td>예약날짜</td><td>총 금액</td><td>예약상태</td></tr>");
			for(int i=0;i<list.size();i++){
				ReserveTicket rt=list.get(i);
				sb.append("<tr><td>"+rt.getRt_tnum()+"</td><td>"+rt.getRt_num()+"</td><td>"+rt.getRt_mnid()+"</td><td>"+rt.getRt_qty()+"</td>"
						+ "<td>"+rt.getRt_date()+"</td><td>"+rt.getRt_total_price()+"</td><td>"+rt.getRt_state()+"</td></tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private void editinfosave(Member mb) { //정보수정(일반)
		mav=new ModelAndView();
		String id = req.getParameter("m_id");
		mb = mDao.getmemberinfo(id);
		String pw = req.getParameter("m_pw");
		String name = req.getParameter("m_name");
		String phone1 = req.getParameter("m_phone1");
		String phone2 =	req.getParameter("m_phone2");
		String phone3 = req.getParameter("m_phone3");
		String email1 = req.getParameter("m_email1");
		String email2 = req.getParameter("m_email2");
		BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
		mb.setM_id(id);
		mb.setM_pw(pwdEncoder.encode(pw));
		mb.setM_email(email1 + "@" + email2);
		mb.setM_name(name);
		mb.setM_phone(phone1 + "-" + phone2 + "-" + phone3);
		System.out.println(mb.getM_name());
		mDao.editinfosave(mb);
		if(session.getAttribute("id") == null){
	          mav.addObject("login", null);
	       }else{
	    	   String sid = session.getAttribute("id").toString();
	    	  mb = mDao.getmemberinfo(sid);
	          mav.addObject("login", mDao.getPart(mb));
	          System.out.println("메인");
	       }
		mav.setViewName("main");
	}
	
	private void editinfosaveseller(Member mb) { //정보수정(판매자)
		mav=new ModelAndView();
		String id = req.getParameter("m_id");
		mb = mDao.getmemberinfo(id);
		String pw = req.getParameter("m_pw");
		String name = req.getParameter("m_name");
		String phone1 = req.getParameter("m_phone1");
		String phone2 =	req.getParameter("m_phone2");
		String phone3 = req.getParameter("m_phone3");
		String email1 = req.getParameter("m_email1");
		String email2 = req.getParameter("m_email2");
		BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
		mb.setM_id(id);
		mb.setM_pw(pwdEncoder.encode(pw));
		mb.setM_email(email1 + "@" + email2);
		mb.setM_name(name);
		mb.setM_phone(phone1 + "-" + phone2 + "-" + phone3);
		System.out.println(mb.getM_name());
		mDao.editinfosave(mb);
		if(session.getAttribute("id") == null){
	          mav.addObject("login", null);
	       }else{
	    	   String sid = session.getAttribute("id").toString();
	    	  mb = mDao.getmemberinfo(sid);
	          mav.addObject("login", mDao.getPart(mb));
	          System.out.println("메인");
	       }
		mav.setViewName("main");
	}
	
	private void idcheck() {
		String id=req.getParameter("id");
		Gson jsonObj=new Gson();
		json=jsonObj.toJson(mDao.idcheck(id));
	}
	
	private void findid() {
		Member mbr=new Member();
		String name=req.getParameter("m_name");
		String email=req.getParameter("m_email");
		mbr.setM_name(name);
		mbr.setM_email(email);
		StringBuilder sb = new StringBuilder();
		if(mDao.findid(mbr)==null){
			sb.append("검색결과 : 아이디가 존재하지 않습니다.");
		}else{
			sb.append("검색결과 : "+mDao.findid(mbr)+"입니다.");
		}
		Gson jsonObj=new Gson();
		json=jsonObj.toJson(sb.toString());
	}
	
	private void findpw() {
		Member mbr=new Member();
		String id=req.getParameter("m_id");
		String email=req.getParameter("m_email");
		mbr.setM_id(id);
		mbr.setM_email(email);
		Gson jsonObj=new Gson();
		json=jsonObj.toJson(mDao.findpw(mbr));
	}

	private void logout(Member mb) { //로그아웃
		 mav=new ModelAndView();
		 session.invalidate();
		 mav.setViewName("main");
		}

	/*private void hashMapTest(String id, String pw) {//Member mb
		  Map<String, String> hmap=new HashMap<String, String>();
		  hmap.put("id", id);
		  hmap.put("pw", pw);
		  mDao.hashMapTest(hmap);
	   }*/
		  
	private void memberAccess(Member mb){ //로그인
        mav=new ModelAndView();
        //비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)은 불가능하다.
         BCryptPasswordEncoder pwEncoder=new BCryptPasswordEncoder(); //필드에 올려도 됨 
         String pwEncode=mDao.getSecurityPwd(mb.getM_id()); //id로 암호화된 pw를 가져옴
         System.out.println("pw="+pwEncode);
         if(pwEncode!=null){
            if(pwEncoder.matches(mb.getM_pw(), pwEncode)){
              session.setAttribute("id", mb.getM_id());
              mb.setM_pw(pwEncode);
              if(session.getAttribute("id") == null){
                 mav.addObject("login", null);
              }else{
                 mav.addObject("login", mDao.getPart(mb));
                 System.out.println("메인");
              }
            }mav.setViewName("main");
         }
   }
	
    private void sellerInsert(Member mb) { //판매자 회원가입
		  mav=new ModelAndView();
		  String view=null;
		  String emt=" ";
		  //비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)은 불가능하다.
		  BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder(); //회원가입, 로그인만
		  //비번을 암호화한 코드로 변환해서 저장
		  mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
		  mb.setM_id(req.getParameter("m_id"));
		  mb.setM_email(req.getParameter("m_email1")+"@"+req.getParameter("m_email2"));
		  mb.setM_name(req.getParameter("m_name"));
		  mb.setM_gender(emt);
		  mb.setM_phone(req.getParameter("m_phone1")+"-"+req.getParameter("m_phone2")+"-"+req.getParameter("m_phone3"));
		  mb.setM_birth(emt);
		  mb.setM_bnum(req.getParameter("m_bnum"));
		  mb.setM_part(req.getParameter("m_radio"));
		  if(mDao.sellerInsert(mb)!=0){
		     view="joinfrmend"; //로그인 페이지
		     mav.addObject("join check",1); //회원가입 성공
		  }else{
		     view="joinfrmseller"; //회원가입 페이지
		      }
		      mav.setViewName(view);
		  }
			
    private void memberInsert(Member mb) { //일반 사용자 회원가입
		  mav=new ModelAndView();
		  String view=null;
		  String emt=" ";
		  //비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)은 불가능하다.
		  BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder(); //회원가입, 로그인만
		  //비번을 암호화한 코드로 변환해서 저장
		  mb.setM_pw(pwdEncoder.encode(mb.getM_pw()));
		  mb.setM_id(req.getParameter("m_id"));
		  mb.setM_email(req.getParameter("m_email1")+"@"+req.getParameter("m_email2"));
		  mb.setM_name(req.getParameter("m_name"));
		  mb.setM_gender(req.getParameter("m_gender"));
		  mb.setM_phone(req.getParameter("m_phone1")+"-"+req.getParameter("m_phone2")+"-"+req.getParameter("m_phone3"));
		  mb.setM_birth(req.getParameter("m_birth1")+req.getParameter("m_birth2")+req.getParameter("m_birth3"));
		  mb.setM_bnum(emt);
		  mb.setM_part("일반");
		  if(mDao.memberInsert(mb)!=0){
		     view="joinfrmend"; //메인 페이지
		     mav.addObject("join check",1); //회원가입 성공
		  }else{
		     view="joinfrm"; //회원가입 페이지
		  }
		  mav.setViewName(view);
    }
}
