package com.TM.LTE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReserveController {

   private ModelAndView mav;   //요청, 저장 끝난후 사라져서 


   @RequestMapping(value = "/airInternals", method = RequestMethod.GET)
   public ModelAndView internalTour() {
      mav = new ModelAndView();
      mav.setViewName("internalair");
      return mav;
   }

   @RequestMapping(value = "/hotelInternals", method = RequestMethod.GET)
   public ModelAndView internalHotel() {
      mav = new ModelAndView();
      mav.setViewName("internalhotel");
      return mav;
   }

   @RequestMapping(value = "/ticketInternals", method = RequestMethod.GET)
   public ModelAndView internalTicket() {
      mav = new ModelAndView();
      mav.setViewName("internalticket");
      return mav;
   }

   @RequestMapping(value = "/airOverseas", method = RequestMethod.GET)
   public ModelAndView toAir() {
      mav = new ModelAndView();
      mav.setViewName("overseasair");
      return mav;
   }

   @RequestMapping(value = "/hotelOverseas", method = RequestMethod.GET)
   public ModelAndView toHotel() {
      mav = new ModelAndView();
      mav.setViewName("overseashotel");
      return mav;
   }

   @RequestMapping(value = "/ticketOverseas", method = RequestMethod.GET)
   public ModelAndView toTicket() {
      mav = new ModelAndView();
      mav.setViewName("overseasticket");
      return mav;
   }
   
}