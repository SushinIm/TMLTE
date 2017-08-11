package com.TM.LTE.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.ReserveTicket;
@Repository
public class ReserveDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int countRtnum() {
		return sqlSession.selectOne("rticket.countRtnum");
	}

	public int maxRtnum() {
		return sqlSession.selectOne("rticket.maxRtnum");
	}

	/*public void insertPayTicket(ReserveTicket rt) {
		sqlSession.insert("rticket.insertPayTicket", rt);
	}*/

}
