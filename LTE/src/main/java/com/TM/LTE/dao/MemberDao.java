package com.TM.LTE.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.BuyerBlack;
import com.TM.LTE.bean.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	public List<Member> adminSeller() {
		return sqlSession.selectList("member.adminSeller");
	}

	public Member getMemberInfo(String id) {
		return sqlSession.selectOne("member.getMemberInfo",id);	
	}

	public Member selectmList(Member mb) {
		return sqlSession.selectOne("member.selectmList", mb);
	}
	//	/		sqlSession.insert("member.addBlack", sbb);


	/*public Member*/ 



	public void addBlack(Member mb) {
		sqlSession.selectList("member.addBlack",mb);
	}



	//	public List<BuyerBlack> addBlack(BuyerBlack bbbean) {
	//		return sqlSession.selectList("member.addBlack",bbbean);
	//	}
	//	
	//	public List<BuyerBlack> getBlackList2() {
	//		return sqlSession.selectList("member.getBlackList2");
	//	}

	public List<Member> adminBuyer() {
		return sqlSession.selectList("member.adminBuyer");
	}

	//	public BuyerBlack selectbbList(String m_bnum) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

	public Member selectmid(String id) {
		return sqlSession.selectOne("member.selectmid",id);
	}

	public List<Member> blackSeller() {
		return sqlSession.selectList("member.blackSeller");
	}


}