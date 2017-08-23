package com.TM.LTE.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.Passenger;
import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ReserveAir;
import com.TM.LTE.bean.ReserveHotel;
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

	public List<ProdRoom> checkLeftRoom(ReserveHotel rh) {
		return sqlSession.selectList("rhotel.checkLeftRoom", rh);
	}

	public int reserveTheRoom(ReserveHotel rh) {
		return sqlSession.insert("rhotel.reserveTheRoom", rh);
	}

	public ReserveHotel selectedRoom(String id) {
		return sqlSession.selectOne("rhotel.selectedRoom", id);
	}
	public void insertPayTicket(ReserveTicket rt) {
		sqlSession.insert("rticket.insertPayTicket", rt);
	}

	public String gethtkrname(String htmid) {
		return sqlSession.selectOne("rhotel.gethtkrname",htmid);
	}

	public String gethtegname(String htmid) {
		return sqlSession.selectOne("rhotel.gethtegname",htmid);
	}

	public int addPassenger(Passenger pass) {
		return sqlSession.insert("rair.addPassenger",pass);
	}

	public int airReserve(ReserveAir ra) {
		// TODO Auto-generated method stub
		return 0;
	}
}
