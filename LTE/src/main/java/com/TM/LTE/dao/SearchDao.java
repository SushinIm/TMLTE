package com.TM.LTE.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.TM.LTE.bean.Image;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.SearchImage;

@Repository
public class SearchDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<Image> getimage() {
		return sqlSession.selectList("Search.imageselect");
	}

	public SearchImage airselect(String pi_num) {
		return sqlSession.selectOne("Search.airselect",pi_num);
	}

	public SearchImage hotelselect(String pi_num) {
		return sqlSession.selectOne("Search.hotelselect",pi_num);
	}
	
	public SearchImage ticketselect(String pi_num) {
		return sqlSession.selectOne("Search.ticketselect",pi_num);
	}

	public List<ProdHotel> hotelsearch1(ReserveHotel rh) {
		return sqlSession.selectList("Search.hotelsearch1", rh);		
	}

	public List<ProdHotel> hotelsearch2(ReserveHotel rh) {
		return sqlSession.selectList("Search.hotelsearch2", rh);
	}

	public Image imageselectnum(String ht_num) {
		return sqlSession.selectOne("Search.imageselectnum", ht_num);
	}
	public ProdRoom hotelselectprice(String ht_mid) {
		return sqlSession.selectOne("Search.hotelselectprice", ht_mid);
	}

	public List<ProdTicket> ticketsearch1(ProdTicket pt) {
		return sqlSession.selectList("Search.ticketsearch1", pt);
	}

	public List<ProdTicket> ticketsearch2(ProdTicket pt) {
		return sqlSession.selectList("Search.ticketsearch2", pt);
	}

	public List<ProdTicket> ticketsearch3(ProdTicket pt) {
		return sqlSession.selectList("Search.ticketsearch3", pt);
	}
}
