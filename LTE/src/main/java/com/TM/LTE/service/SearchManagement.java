package com.TM.LTE.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class SearchManagement {
	
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
