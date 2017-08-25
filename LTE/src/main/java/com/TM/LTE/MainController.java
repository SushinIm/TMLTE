package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Member;
import com.TM.LTE.service.MemberManagement;

@Controller
public class MainController {
	private ModelAndView mav;   //요청, 저장 끝난후 사라져서 

	@Autowired //가져다 쓸때 사용
	private MemberManagement mm;//회원관리 서비스 클래스
	
	@RequestMapping(value = "/main") //회원가입(일반)
	public ModelAndView main(Member mb) { //String -> model.addAtrribute("home");
		mav=mm.execute(mb, 0); //로그인
    	return mav;
	}
	
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb) { //id,pw가 들어있음
		mav=mm.execute(mb, 1); //로그인
		return mav;
	}
		
	@RequestMapping(value = "/memberInsert", method = RequestMethod.POST) //post, get일시 예측불가일때는 지우기
	public ModelAndView memberInsert(@ModelAttribute("mb") Member mb) { //로그인시 id,pw만 쓰면됨 회원가입시 너무 많아짐
		mav=mm.execute(mb, 2);
		return mav;
	} 
	
	@RequestMapping(value = "/sellerInsert", method = RequestMethod.POST) //post, get일시 예측불가일때는 지우기
	public ModelAndView sellerInsert(@ModelAttribute("mb") Member mb) { //로그인시 id,pw만 쓰면됨 회원가입시 너무 많아짐
		mav=mm.execute(mb, 3);
		return mav;
	} 
	
	@RequestMapping(value = "/logout") //로그아웃
	public ModelAndView logout(@ModelAttribute("mb") Member mb) { //String -> model.addAtrribute("home");
		mav=mm.execute(mb, 4);
		return mav;
	}
	
	@RequestMapping(value = "/mypage") //마이페이지(일반)
	public ModelAndView mypage(@ModelAttribute("mb") Member mb) { //String -> model.addAtrribute("home");
		mav=mm.execute(mb, 5);
		return mav;
	}
	
	@RequestMapping(value = "/editinfo") //정보수정전 비밀번호 체크
	public ModelAndView editinfo(@ModelAttribute("mb") Member mb) { //String -> model.addAtrribute("home");
		mav=mm.execute(mb, 7);
		return mav;
	}
	
	@RequestMapping(value = "/editinfoseller") //정보수정전 비밀번호 체크
	public ModelAndView editinfoseller(@ModelAttribute("mb") Member mb) { //String -> model.addAtrribute("home");
		mav=mm.execute(mb, 8);
		return mav;
	}
	
	@RequestMapping(value = "/editinfosave") //정보수정(일반)
    public ModelAndView editinfosave(@ModelAttribute("mb") Member mb) {
		mav=mm.execute(mb, 9);
		return mav;
	}
	
	@RequestMapping(value = "/editinfosaveseller") //정보수정(판매자)
    public ModelAndView editinfosaveseller(@ModelAttribute("mb") Member mb) { 
		mav=mm.execute(mb, 10);
		return mav;
	}
	@RequestMapping(value = "/hotelbook")
	public ModelAndView hotelbook(@ModelAttribute("mb") Member mb) {
		mav=mm.execute(mb, 11);
		return mav;
	}
	@RequestMapping(value = "/ticket")
	public ModelAndView ticket(@ModelAttribute("mb") Member mb) {
		mav=mm.execute(mb, 12);
		return mav;
	}
	@RequestMapping(value = "/airbook")
	public ModelAndView airbook(@ModelAttribute("mb") Member mb) {
		mav=mm.execute(mb, 13);
		return mav;
	}
	
	@RequestMapping(value = "ajax/idcheck") //아이디 중복체크
	public @ResponseBody String idcheck() {
		String json=mm.execute(1);
		return json;
	}
	
	@RequestMapping(value = "ajax/findid") //아이디 찾기
	public @ResponseBody String findid() {
		String json=mm.execute(2);
		return json;
	}
	
	@RequestMapping(value = "ajax/findpw") //아이디 찾기
	public @ResponseBody String findpw() {
		String json=mm.execute(3);
		return json;
	}
	
	//--------------------------------Page Move-----------------------------
	
	@RequestMapping(value = "/finding") //아이디/비밀번호 찾기
    public ModelAndView finding() { //String -> model.addAtrribute("home");
	   mav=new ModelAndView(); //Moving Class이용해도 됨 ->execute사용
	   mav.setViewName("finding"); //.jsp 회원가입 페이지
	   return mav;
	}
	
	@RequestMapping(value = "/editinfobefore") //아이디/비밀번호 찾기 전 페이지
    public ModelAndView editinfobefore() { //String -> model.addAtrribute("home");
       mav=new ModelAndView(); //Moving Class이용해도 됨 ->execute사용
       mav.setViewName("editinfobefore"); //.jsp 회원가입 페이지
       return mav;
	}
	
	@RequestMapping(value = "/editinfobeforeseller") //아이디/비밀번호 찾기 전 페이지
	public ModelAndView editinfobeforeseller() { //String -> model.addAtrribute("home");
	   mav=new ModelAndView(); //Moving Class이용해도 됨 ->execute사용
	   mav.setViewName("editinfobeforeseller"); //.jsp 회원가입 페이지
	   return mav;
	}
	
	@RequestMapping(value = "/next") //회원가입(일반)
	   public ModelAndView next() { //String -> model.addAtrribute("home");
	      mav=new ModelAndView(); //Moving Class이용해도 됨 ->execute사용
	      mav.setViewName("joinfrmend"); //.jsp 회원가입 페이지
	      return mav;
	}
	
	@RequestMapping(value = "/joinfrm") //회원가입(일반)
	   public ModelAndView joinfrm() { //String -> model.addAtrribute("home");
	      mav=new ModelAndView(); //Moving Class이용해도 됨 ->execute사용
	      mav.setViewName("joinfrm"); //.jsp 회원가입 페이지
	      return mav;
	}	
	
	@RequestMapping(value = "/joinfrmseller") //회원가입(판매자)
		public ModelAndView joinfrmseller() { //String -> model.addAtrribute("home");
			mav=new ModelAndView(); //Moving Class이용해도 됨 ->execute사용
			mav.setViewName("joinfrmseller"); //.jsp 회원가입 페이지
			return mav;
	}
	
	@RequestMapping(value = "/joinfrmbefore") //회원가입 전 동의
		public ModelAndView joinfrmbefore() { //String -> model.addAtrribute("home");
			mav=new ModelAndView(); //Moving Class이용해도 됨 ->execute사용
			mav.setViewName("joinfrmbefore"); //.jsp 회원가입 페이지
			return mav;
	}
	
}
