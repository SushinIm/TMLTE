package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Member;
import com.TM.LTE.service.SearchManagement;

@Controller
public class SearchController {
	private ModelAndView mav;   
	
	@Autowired
	private SearchManagement sm;
	
	@RequestMapping(value = "/ImageSearch")
	public ModelAndView imagesearch(MultipartHttpServletRequest multi) {
		//이미지 검색 부분
		System.out.println("ImageSearch");
		mav=new ModelAndView();
		mav=sm.execute(multi,1);  //검색서비스 이동
		return mav;
	}
	
	@RequestMapping(value = "ajax/hotelsearch")
	public @ResponseBody String hotelsearch() {
		String json=sm.execute(1);
		return json;
	}
	
	@RequestMapping(value = "ajax/ticketsearch")
	public @ResponseBody String ticketsearch() {
		String json=sm.execute(2);
		return json;
	}
}











