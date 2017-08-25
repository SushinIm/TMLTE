package com.TM.LTE.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.AirType;

@Repository
public class AirDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insertAirtype(AirType at) {
		return sqlSession.insert("airtype.insertAirtype", at);
	}

	public List<AirType> selectAirtype() {
		return sqlSession.selectList("airtype.selectAirtype");
	}
}
