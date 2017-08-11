package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.service.SearchManagement;

@Controller
public class SearchController {
	private ModelAndView mav;   
	
	@Autowired
	private SearchManagement sm;
	
	/*@RequestMapping(value = "/contents")
	   public ModelAndView contents() {
		  System.out.println("asdf");
	      mav = new ModelAndView();
	      mav = sm.execute(1);
	      return mav;
	   }*/
	
	@RequestMapping(value = "/imagesearch")
	public ModelAndView imagesearch(MultipartHttpServletRequest multi) {
		//DB에서 게시판 리스트를 가져오는 서비스 제공
		System.out.println("imagesearch");
		mav=new ModelAndView();
		//System.out.println("title="+multi.getParameter("btitle"));
		//MultipartFile mfile=multi.getFile("bfile");
		//System.out.println("file name="+mfile.getOriginalFilename());  //파일이름
		//System.out.println("file name="+mfile.getName()); --> 파라미터이름
		mav=sm.execute(multi,1);  //글쓰기 화면 이동
		return mav;
	}
}
