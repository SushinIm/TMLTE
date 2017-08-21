package com.TM.LTE;

import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.TM.LTE.bean.Email;
import com.TM.LTE.service.EmailManagement;
import com.TM.LTE.userClass.EmailSender;

@Controller
public class MailController {
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private Email email;
	
	@Autowired
	private EmailManagement em;
	
	private ModelAndView mav;
   
	private String newpw;
	
    @RequestMapping(value = "/sendMail/pw")
    public ModelAndView sendEmailActionPw (@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
        String id=(String) paramMap.get("id");
        String e_mail=(String) paramMap.get("email");
        System.out.println(id+e_mail);
        int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        newpw = String.valueOf(ran);
        em.exacute(paramMap,newpw,1);
        System.out.println(newpw);
        if(newpw!=null) {
            email.setContent("임시비밀번호는 "+newpw+" 입니다.");
            email.setReceiver(e_mail);
            email.setSubject(id+"님 비밀번호 찾기 메일입니다.");
            emailSender.SendEmail(email);
        } 
        mav= new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }
}