package com.TM.LTE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.lee.dao.MemberDao;

@Service
public class AdminManagement {
	@Autowired
	private MemberDao mDao;
}
