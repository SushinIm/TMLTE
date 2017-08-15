package com.TM.LTE.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.Member;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<Member> adminSeller() {
		return sqlSession.selectList("member.adminSeller");
	}
	
	/*public Member getMemberInfo(String id) {
		return sqlSession.selectOne("member.getMemberInfo",id);	
	}*/
	
}
