package com.TM.LTE.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.Image;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
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

	public List<ReserveHotel> getHotelReserveList(String id) {
		return sqlSession.selectList("rhotel.getHotelReserveList", id);
	}

	public List<ReserveTicket> getTicketReserveList(int prod) {
		return sqlSession.selectList("rticket.getTicketReserveList", prod);
	}

	public int selectProd(String id) {
		return sqlSession.selectOne("pticket.selectProd", id);
	}

	public int BestUpdateH(String ht_mid) {
		return sqlSession.update("photel.BestUpdateH", ht_mid);
	}

	public int BestUpdateT(int t_num) {
		return sqlSession.update("pticket.BestUpdateT", t_num);
	}

	/*public int hotelWrite(LinkedHashMap<String, String> fMap) {
		System.out.println("Dddd");
		int f=fileInsert(fMap);
		System.out.println(f);
		if(f != 0)
			return 1;	//tx 성공시
		return 0; 	//tx 실패시
	}*/

	public int fileInsert(Image im) {
		return sqlSession.insert("photel.fileInsert", im);
	}

	public int insertWrite(ProdHotel prodhotel) {
		return sqlSession.insert("photel.insertWrite", prodhotel);
	}

	public String hotelNum(String id) {
		return sqlSession.selectOne("photel.hotelNum", id);
	}

	public List<Image> selectName(String hnum) {
		return sqlSession.selectList("image.selectName", hnum);
	}

	public ProdHotel selectUpFrm(String hnum) {
		return sqlSession.selectOne("photel.selectUpFrm", hnum);
	}

	public int insertRoom(ProdHotel prodhotel) {
		return sqlSession.insert("photel.insertRoom", prodhotel);
	}

}
