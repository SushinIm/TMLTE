package com.TM.LTE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class SearchManagement {
	@Autowired
	private ModelAndView mav;

	public ModelAndView execute(int code) {
		switch(code){
	      case 1:
	         getSearchContents(); break;
	         
	      default:break;
	      }
	      return mav;
	}

	private void getSearchContents() {
		
	}
	
}
