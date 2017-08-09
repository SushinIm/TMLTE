package com.TM.LTE.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdTicket;
@Repository
public class SellerDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public String selectPart(String id) {
		return sqlSession.selectOne("photel.selectPart", id);
	}

	public List<ProdHotel> getHotelProdList(String id) {
		return sqlSession.selectList("photel.getHotelProdList", id);
	}

	public List<ProdTicket> getTicketProdList(String id) {
		return sqlSession.selectList("pticket.get-getTicketProdList", id);
	}

	
}
