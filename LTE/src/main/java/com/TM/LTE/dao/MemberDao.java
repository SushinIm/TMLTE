package com.TM.LTE.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TM.LTE.bean.Member;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveAir;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.google.gson.JsonElement;

@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Member> adminSeller() {
		return sqlSession.selectList("member.adminSeller");
	}

	public int memberInsert(Member mb) { //회원가입(일반)
		return sqlSession.insert("member.memberInsert", mb); //변수나 Bean을 넘겨줌
	}
	
	public int sellerInsert(Member mb) { //회원가입(판매자)
		return sqlSession.insert("member.sellerInsert", mb);
	}
	
	public String getSecurityPwd(String m_id) { 
		return sqlSession.selectOne("member.getSecurityPw", m_id);

	}
<<<<<<< HEAD
	public void addBlack(Member mb) {
		sqlSession.selectList("member.addBlack",mb);
	}

	public List<Member> adminBuyer() {
		return sqlSession.selectList("member.adminBuyer");
	}

	public Member selectmid(String id) {
		return sqlSession.selectOne("member.selectmid",id);
=======
	public int hashMapTest(Map<String, String> hmap) { 
		return sqlSession.selectOne("member.hashMapTest",hmap);		
	}

	public String getPart(Member mb) { //로그인 구분
		return sqlSession.selectOne("member.getPart", mb);
	}

	public String idcheck(String id) {
		return sqlSession.selectOne("member.idcheck", id);
	}

	public Member mypage(String id) { //마이페이지(일반)
		return sqlSession.selectOne("member.mypage",id);   
	}
	
	public Member mypageseller(String id) { //마이페이지(판매자)
		return sqlSession.selectOne("member.mypageseller",id);
	}

	public Member getmemberinfo(String id) { //비밀번호 일치시 멤버정보 가져오기
		return sqlSession.selectOne("member.getmemberinfo",id);
	}
	
	public int updatepw(Map<String, Object> paramMap) {
		return sqlSession.update("member.updatepw", paramMap);
	}
	
	public List<ReserveAir> getrair(String id) {
		return sqlSession.selectList("rair.getrair", id);
	}

	public List<ReserveHotel> getrhotel(String id) {
		return sqlSession.selectList("rhotel.getrhotel", id);
	}

	public List<ReserveTicket> getrticket(String id) {
		return sqlSession.selectList("rticket.getrticket", id);
	}

	public Member getmemberpart(String id) {
		return sqlSession.selectOne("member.getmemberpart", id);
	}

	public List<ProdHotel> getphotel(String id) {
		return sqlSession.selectList("photel.getphotel", id);
	}

	public List<ProdTicket> getpticket(String id) {
		return sqlSession.selectList("pticket.getpticket", id);
	}

	public List<ProdRoom> getphotelroom(String id) {
		return sqlSession.selectList("photelroom.getphotelroom", id);
	}
	
	public String findid(Member mb) {
		return sqlSession.selectOne("member.findid", mb);
	}

	public int editinfosave(Member mb){ //정보수정(일반) , (판매자)
		return sqlSession.update("member.editinfosave",mb);
>>>>>>> 5dea1fb7d4188a66214634538c785d394404db0a
	}

	public String findpw(Member mbr) {
		return sqlSession.selectOne("member.findpw", mbr);
	}
}


