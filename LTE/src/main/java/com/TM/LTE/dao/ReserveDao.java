package com.TM.LTE.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.bean.RoomView;
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

	public List<ProdRoom> checkLeftRoom(RoomView rv) {
		return sqlSession.selectList("rhotel.checkLeftRoom", rv);
	}

	public int reserveTheRoom(ReserveHotel rh) {
		return sqlSession.insert("rhotel.reserveTheRoom", rh);
	}

	public List<ReserveHotel> selectedRoom(String id) {
		return sqlSession.selectOne("rhotel.selectedRoom", id);
	}
	/*public void insertPayTicket(ReserveTicket rt) {
		sqlSession.insert("rticket.insertPayTicket", rt);
	}*/

}
