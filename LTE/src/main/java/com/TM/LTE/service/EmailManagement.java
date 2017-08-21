package com.TM.LTE.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.TM.LTE.dao.MemberDao;

@Service
public class EmailManagement {
	
	@Autowired
	private MemberDao mdao;
	
	private String pw;
	
	public void exacute(Map<String, Object> paramMap, String newpw, int i) {
		switch(i){
		case 1:
			updatepw(paramMap, newpw);
			break;
			
		default:
			break;
		}
	}

	private void updatepw(Map<String, Object> paramMap, String newpw) {
        BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
        paramMap.put("newpw", pwdEncoder.encode(newpw));
        mdao.updatepw(paramMap);
	}
}
