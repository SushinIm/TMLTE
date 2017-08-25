package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.service.SellerManagement;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {
	@Autowired
	private SellerManagement sm;
	
	private ModelAndView mav;
	
	//@ResponseBody : 리턴값을 response 객체를 이용해서 요청했던 페이지로 넘겨준다.
	@RequestMapping(value = "/updateClick")
	public @ResponseBody String updateClick() {
		//DB에서 댓글ㅇ르 입력후 댓글리스트를 json형태로 반환
		System.out.println("ajax/updateClick");
		String jsonStr=sm.executeAjax(1);
		return jsonStr;
	}
	@RequestMapping(value = "/bestProd")
	public @ResponseBody String bestProd() {
		System.out.println("ajax/bestProd");
		String jsonStr=sm.executeAjax(2);
		return jsonStr;
	}
	@RequestMapping(value = "/relProd")
	public @ResponseBody String relProd() {
		System.out.println("ajax/relProd");
		String jsonStr=sm.executeAjax(3);
		return jsonStr;
	}
}