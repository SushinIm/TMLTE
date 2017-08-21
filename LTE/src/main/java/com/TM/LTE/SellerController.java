package com.TM.LTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.service.SellerManagement;


@Controller
public class SellerController {
	@Autowired
	private SellerManagement sm;
	
	private ModelAndView mav;   
	
	@RequestMapping(value = "/mypageFrm", method = RequestMethod.GET)
	public ModelAndView mypageFrm() {
		System.out.println("mypageFrm");
		mav=new ModelAndView();
		mav = sm.execute(1);
		//mav.setViewName("sellerpage");//.jsp
		return mav;
	}
	@RequestMapping(value = "/bestProd", method = RequestMethod.GET)
	public ModelAndView bestProd() {
		System.out.println("bestProd");
		mav=new ModelAndView();
		mav = sm.execute(2);
		//mav.setViewName("sellerpage");//.jsp
		return mav;
	}
	
	@RequestMapping(value = "/hotelUpdateFrm", method = RequestMethod.GET)
	public ModelAndView hotelUpdateFrm() {
		System.out.println("hotelUpdateFrm");
		mav=new ModelAndView();
		mav = sm.execute(3);
		return mav;
	}
	@RequestMapping(value = "/hotelDeleteFrm", method = RequestMethod.GET)
	public ModelAndView hotelDeleteFrm() {
		System.out.println("hotelDeleteFrm");
		mav=new ModelAndView();
		mav = sm.execute(4);
		return mav;
	}
	/*@RequestMapping(value = "/updateClick", method = RequestMethod.POST)
	public ModelAndView updateClick() {
		System.out.println("updateClick");
		mav=new ModelAndView();
		mav = sm.execute(5);
		return mav;
	}
	@RequestMapping(value = "/deleteClick", method = RequestMethod.POST)
	public ModelAndView deleteClick() {
		System.out.println("deleteClick");
		mav=new ModelAndView();
		mav = sm.execute(6);
		return mav;
	}*/
	
	@RequestMapping(value = "/hotelWrite")
	public ModelAndView hotelWrite(MultipartHttpServletRequest multi) {
		System.out.println("hotelWrite2");
		MultipartFile mfile1 = multi.getFile("mainfile");
		MultipartFile mfile2 = multi.getFile("subfile");
		MultipartFile mfile3 = multi.getFile("etcfile");
		System.out.println("file name=" + mfile1.getOriginalFilename());
		System.out.println("file name=" + mfile2.getOriginalFilename());
		System.out.println("file name=" + mfile3.getOriginalFilename());
		mav=new ModelAndView();
		mav = sm.execute(multi, 1);
		return mav;
	}
	@RequestMapping(value = "/allupdateclick")
	public ModelAndView allupdateclick(MultipartHttpServletRequest multi) {
		System.out.println("allupdateclick");
		MultipartFile mfile1 = multi.getFile("mainfile");
		MultipartFile mfile2 = multi.getFile("subfile");
		MultipartFile mfile3 = multi.getFile("etcfile");
		System.out.println("file name=" + mfile1.getOriginalFilename());
		System.out.println("file name=" + mfile2.getOriginalFilename());
		System.out.println("file name=" + mfile3.getOriginalFilename());
		mav=new ModelAndView();
		mav = sm.execute(multi, 2);
		return mav;
	}
	
}
