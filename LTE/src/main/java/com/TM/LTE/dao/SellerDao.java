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
import com.TM.LTE.bean.ProdRoom;
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
		return sqlSession.selectList("pticket.getTicketProdList", id);
	}

	public List<ReserveHotel> getHotelReserveList(String id) {
		return sqlSession.selectList("rhotel.getHotelReserveList", id);
	}

	public List<ReserveTicket> getTicketReserveList(String id) {
		return sqlSession.selectList("rticket.getTicketReserveList", id);
	}
	
	public ProdHotel selectProdH(String id) {
		return sqlSession.selectOne("photel.selectProdH", id);
	}
	
	public ProdTicket selectProdT(String id) {
		return sqlSession.selectOne("pticket.selectProdT", id);
	}

	public int BestUpdateH(String id) {
		return sqlSession.update("photel.BestUpdateH", id);
	}

	public int BestUpdateT(String num) {
		return sqlSession.update("pticket.BestUpdateT", num);
	}

	/*public int hotelWrite(LinkedHashMap<String, String> fMap) {
		System.out.println("Dddd");
		int f=fileInsert(fMap);
		System.out.println(f);
		if(f != 0)
			return 1;	//tx �꽦怨듭떆
		return 0; 	//tx �떎�뙣�떆
	}*/

	public int fileInsert(Image im) {
		System.out.println("dao");
		return sqlSession.insert("photel.fileInsert", im);
	}

	public int insertWrite(ProdHotel prodhotel) {
		System.out.println("insesrtWrite");
		return sqlSession.insert("photel.insertWrite", prodhotel);
	}

	public String hotelNum(String id) {
		System.out.println("select");
		return sqlSession.selectOne("photel.hotelNum", id);
	}

	public List<Image> selectName(String pnum) {
		return sqlSession.selectList("image.selectName", pnum);
	}

	public ProdHotel selectUpFrm(String pnum) {
		return sqlSession.selectOne("photel.selectUpFrm", pnum);
	}

	public int insertRoom(ProdRoom proom) {
		System.out.println("insesrtR");
		return sqlSession.insert("proom.insertRoom", proom);
	}

	public List<ProdRoom> selectRoom(String id) {
		return sqlSession.selectList("proom.selectRoom", id);
	}
	public List<ProdRoom> selectRoomN(ProdRoom pr) {
		return sqlSession.selectList("proom.selectRoomN", pr);
	}

	public int deleteOneRoom(ProdRoom pr) {
		return sqlSession.delete("proom.deleteOneRoom", pr);
	}

	public int updateText(ProdHotel ph) {
		return sqlSession.update("photel.updateText", ph);
	}

	public int deleteFileImage(Image im) {
		return sqlSession.delete("image.deleteFileImage", im);
	}
	public int deleteProd(String id, String pnum) {
		if(sqlSession.delete("image.deleteImage", pnum)==1){
			if(sqlSession.delete("proom.deleteRoom", id)==1){
				sqlSession.delete("photel.deleteHotel", id);
				return 1;
			}
		}
		return 0;
	}

	public int updateRoom(ProdRoom pr) {
		return sqlSession.update("proom.updateRoom", pr);
	}

	public int relUpdateH(String id) {
		return sqlSession.update("photel.relUpdateH", id);
	}

	public int relUpdateT(String num) {
		return sqlSession.update("pticket.relUpdateT", num);
	}

	public String selectBest(String id, String part) {
		String best = null;
		if(part.equals("숙박")){
			 best = sqlSession.selectOne("photel.selectBest", id);
		}else if(part.equals("레저/입장권")){
			best = sqlSession.selectOne("pticket.selectBest", id);
		}
		 return best;
	}

}
