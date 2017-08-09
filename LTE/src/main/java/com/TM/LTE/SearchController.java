package com.TM.LTE;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.service.SearchManagement;

	public class SearchController {
		private ModelAndView mav;   
		
		@Autowired
		private SearchManagement sm;
		
		@RequestMapping(value = "/contents")
		   public ModelAndView contents() {
		      mav = new ModelAndView();
		      mav = sm.execute(1);
		      return mav;
		   }
}
