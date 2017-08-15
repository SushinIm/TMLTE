package com.TM.LTE.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.TM.LTE.bean.Image;
import com.TM.LTE.bean.SearchImage;

@Repository
public class SearchDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<Image> getimage() {
		return sqlSession.selectList("Search.imageselect");
	}

	public List<SearchImage> airselect(String pi_num) {
		return sqlSession.selectList("Search.airselect",pi_num);
	}

	public List<SearchImage> hotelselect(String pi_num) {
		return sqlSession.selectList("Search.hotelselect",pi_num);
	}
	
	public List<SearchImage> ticketselect(String pi_num) {
		return sqlSession.selectList("Search.ticketselect",pi_num);
	}

}
