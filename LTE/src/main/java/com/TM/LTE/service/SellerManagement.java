package com.TM.LTE.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.TM.LTE.bean.Image;
import com.TM.LTE.bean.ProdHotel;
import com.TM.LTE.bean.ProdRoom;
import com.TM.LTE.bean.ProdTicket;
import com.TM.LTE.bean.ReserveHotel;
import com.TM.LTE.bean.ReserveTicket;
import com.TM.LTE.dao.SellerDao;
import com.TM.LTE.userClass.UploadFile1;
import com.google.gson.Gson;

@Service
public class SellerManagement {
   private ModelAndView mav;
   private String jsonStr;
   @Autowired
   private HttpSession session;
   @Autowired
   private HttpServletRequest req;
   @Autowired
   private SellerDao sDao;

   public String executeAjax(int i) {
      switch(i){
      case 1:
         updateClick();break;
      default:
         break;
      }
      return jsonStr;
   }
   private void updateClick() {
      String view = null;
      mav = new ModelAndView();
      String id = "HBB";//session.getAttribute("id").toString();
      String r_num = req.getParameter("r_num");
      String r_name = req.getParameter("r_name");
      String r_price = req.getParameter("r_price");
      String r_person = req.getParameter("r_person");
      ProdRoom pr = new ProdRoom();
      pr.setHtr_htmid(id);
      pr.setHtr_rnum(r_num);
      pr.setHtr_name(r_name);
      pr.setHtr_price(r_price);
      pr.setHtr_pnum(r_person);
      if(sDao.updateRoom(pr)==1){
         System.out.println("기존 방 수정 성공");
         List<ProdRoom> pList = sDao.selectRoomN(pr);
         Gson jsonObj = new Gson();
         jsonStr = jsonObj.toJson(pList);
         System.out.println("jsonStr = " + jsonStr);
      }else{
         System.out.println("기존 방 수정 실패");
         view = "hotelupdate";
      }
      mav.setViewName(view);
   }
   public ModelAndView execute(MultipartHttpServletRequest multi, int cmd) {
      switch(cmd){
      case 1:
         hotelWrite(multi);break;
      case 2:
         allupdateclick(multi); break;
      default:
         break;
      }
      return mav;
   }
   private void allupdateclick(MultipartHttpServletRequest multi) {
      String pnum = multi.getParameter("pnum");
      String br_num = multi.getParameter("br_num");
      int maxcount = Integer.valueOf(multi.getParameter("maxcount"));
      System.out.println(multi.getParameter("count"));
      int count = Integer.valueOf(multi.getParameter("count"));
      System.out.println(pnum);
      String id = "HBB";//session.getAttribute("id").toString();
      String krname = multi.getParameter("krname");
      String egname = multi.getParameter("egname");
      String msphone = multi.getParameter("msphone");
      String addr = multi.getParameter("addr");
      String r_part1 = "main";
      String r_part2 = "sub";
      String r_part3 = "주의사항";
      
      ProdHotel ph = new ProdHotel();
      LinkedHashMap<String, String> fMap = null;
      
      ProdRoom pr = new ProdRoom();
      pr.setHtr_htmid(id);
      pr.setHtr_rnum(br_num);
      if(sDao.deleteOneRoom(pr)==1) {
         System.out.println("삭제 성공하였습니다.");
      }else {
         System.out.println("삭제 실패하였습니다.");
      }
      
      ph.setHt_mid(id);
      ph.setHt_krname(krname);
      ph.setHt_egname(egname);
      ph.setHt_msphone(msphone);
      ph.setHt_addr(addr);
      sDao.updateText(ph);
      System.out.println("text 수정성공");
      String detail = "숙박";
      System.out.println(pnum);
      Image im = new Image();
      
      MultipartFile mfile1 = multi.getFile("mainfile");
      MultipartFile mfile2 = multi.getFile("subfile");
      MultipartFile mfile3 = multi.getFile("etcfile");
      
      int check1, check2, check3;
      System.out.println(multi.getParameter("fileCheck1"));
      System.out.println(multi.getParameter("fileCheck1").length());
      if(multi.getParameter("fileCheck1").equals("")){
         System.out.println("test");
         check1 = -1;
      } else {
         check1 = Integer.parseInt(multi.getParameter("fileCheck1"));
      }
      if(multi.getParameter("fileCheck2").equals("")){
         check2 = -1;
      }else {
         check2 = Integer.parseInt(multi.getParameter("fileCheck2"));
      }
      if(multi.getParameter("fileCheck3").equals("")){
         check3 = -1;
      }else {
         check3 = Integer.parseInt(multi.getParameter("fileCheck3"));
      }
      
      if(check1==1 || check2 == 1 || check3 ==1){
         UploadFile1 upload=new UploadFile1();
         fMap = upload.fileUp(multi, pnum, detail);
      }
      if(mfile1.getOriginalFilename() != ""){
         im.setPi_num(pnum);
         im.setPi_part(r_part1);
         im.setPi_oriname(fMap.get("oriFileName0"));
         im.setPi_sysname(fMap.get("sysFileName0"));
         im.setPi_path(fMap.get("path0"));
         im.setPi_num(fMap.get("pnum0"));
         im.setPi_detail(fMap.get("detail0"));
         im.setPi_part(r_part1);
         sDao.fileInsert(im);
         System.out.println("이미지1 수정성공");
      }else {
          System.out.println("이미지1 수정 없음");
      }
      if(mfile2.getOriginalFilename() != ""){
         im.setPi_num(pnum);
         im.setPi_part(r_part2);
         sDao.deleteFileImage(im);
         im.setPi_oriname(fMap.get("oriFileName1"));
         im.setPi_sysname(fMap.get("sysFileName1"));
         im.setPi_path(fMap.get("path1"));
         im.setPi_num(fMap.get("pnum1"));
         im.setPi_detail(fMap.get("detail1"));
         im.setPi_part(r_part2);
         sDao.fileInsert(im);
         System.out.println("이미지2 수정성공");
      }else {
         System.out.println("이미지2 수정 없음");
      }
      if(mfile3.getOriginalFilename() != ""){
         im.setPi_num(pnum);
         im.setPi_part(r_part3);
         sDao.deleteFileImage(im);
         im.setPi_oriname(fMap.get("oriFileName2"));
         im.setPi_sysname(fMap.get("sysFileName2"));
         im.setPi_path(fMap.get("path2"));
         im.setPi_num(fMap.get("pnum2"));
         im.setPi_detail(fMap.get("detail2"));
         im.setPi_part(r_part3);
         sDao.fileInsert(im);
         System.out.println("이미지3 수정성공");
      }else {
         System.out.println("이미지3 수정 없음");
      }
      
      System.out.println(count+"  /  " + maxcount);
      if(count <= maxcount){
         for(int i=count+1; i < maxcount+1; i++){
            ProdRoom prodroom = new ProdRoom();
            String name = multi.getParameter("r_name"+i);
            String num = multi.getParameter("r_num"+i);
            String price = multi.getParameter("r_price"+i);
            String person = multi.getParameter("r_person"+i);
            prodroom.setHtr_htmid(id);
            prodroom.setHtr_rnum(num);
            prodroom.setHtr_name(name);
            prodroom.setHtr_price(price);
            prodroom.setHtr_pnum(person);
            if(sDao.insertRoom(prodroom) == 1){
               System.out.println("Room insert성공");
            }else{
               System.out.println("Room insert실패");
            }
         }
      }else{
         System.out.println("추가된 방이 없습니다.");
      }
      mav = new ModelAndView();
      mav.setViewName("hoteldetail");
   }
   //숙박 상품 등록
   private void hotelWrite(MultipartHttpServletRequest multi) {
      System.out.println("서비스");
      String id = "HBB";//session.getAttribute("id").toString();
      String krname = multi.getParameter("krname");
      String egname = multi.getParameter("egname");
      String nationSel = multi.getParameter("nationSel");
      String citySel = multi.getParameter("citySel");
      String phonefirst = multi.getParameter("phonefirst");
      String phonecenter = multi.getParameter("phonecenter");
      String phonelast = multi.getParameter("phonelast");
      String addr = multi.getParameter("addr");
      String r_part1 = "main";
      String r_part2 = "sub";
      String r_part3 = "주의사항";
      
      ProdHotel prodhotel = new ProdHotel();
      prodhotel.setHt_mid(id);
      prodhotel.setHt_krname(krname);
      prodhotel.setHt_egname(egname);
      prodhotel.setHt_nation(nationSel);
      prodhotel.setHt_city(citySel);
      prodhotel.setHt_msphone(phonefirst+phonecenter+phonelast);
      prodhotel.setHt_addr(addr);
      
      int count = Integer.valueOf(multi.getParameter("count"));
      if(sDao.insertWrite(prodhotel) == 1){
         System.out.println("HotelProd insert성공");
         for(int i=0; i < count+1; i++){
            ProdRoom prodroom = new ProdRoom();
            String name = multi.getParameter("r_name"+i);
            String num = multi.getParameter("r_num"+i);
            String price = multi.getParameter("r_price"+i);
            String person = multi.getParameter("r_person"+i);
            prodroom.setHtr_htmid(id);
            prodroom.setHtr_rnum(num);
            prodroom.setHtr_name(name);
            prodroom.setHtr_price(price);
            prodroom.setHtr_pnum(person);
            if(sDao.insertRoom(prodroom) == 1){
               System.out.println("Room insert성공");
            }else{
               System.out.println("Room insert실패");
            }
         }
      }else{
         System.out.println("HotelProd insert실패");
      }
      int check1 = Integer.parseInt(multi.getParameter("fileCheck1"));
      int check2 = Integer.parseInt(multi.getParameter("fileCheck2"));
      int check3 = Integer.parseInt(multi.getParameter("fileCheck3"));
      LinkedHashMap<String, String> fMap = null;
      String pnum = sDao.hotelNum(id);
      System.out.println(pnum);
      String detail = "숙박";
      if(check1==1 && check2 == 1 && check3 ==1){
         UploadFile1 upload=new UploadFile1();
         fMap = upload.fileUp(multi, pnum, detail);
      }
      Image im = new Image();
      
      mav = new ModelAndView();
      for(int i =0; i < 3; i++){
         im.setPi_oriname(fMap.get("oriFileName"+i));
         im.setPi_sysname(fMap.get("sysFileName"+i));
         im.setPi_path(fMap.get("path"+i));
         im.setPi_num(fMap.get("pnum"+i));
         im.setPi_detail(fMap.get("detail"+i));
         if(i == 0){
            im.setPi_part(r_part1);
         }else if(i == 1){
            im.setPi_part(r_part2);
         }else{
            im.setPi_part(r_part3);
         }
         sDao.fileInsert(im);
         mav.addObject("sysFileName"+i, fMap.get("sysFileName"+i));
      }
      System.out.println("count" + count);
      
      mav.addObject("pnum", pnum);
      mav.addObject("count", count);
      mav.setViewName("proddetail");
   }

   public ModelAndView execute(int i) {
      switch(i){
      case 1:
         getSellerList(); break;
      case 2:
         getBestProdUpdate(); break;
      case 3:
         hotelUpdateFrm(); break;
      case 4:
         hotelDeleteFrm(); break;
      /*case 5:
         updateClick(); break;*/
      }
      return mav;
   }
   /*private void updateClick() {
      String id = "HBB";//session.getAttribute("id").toString();
      int r_num = Integer.valueOf(req.getParameter("r_num"));
      String r_name = req.getParameter("r_name");
      String r_price = req.getParameter("r_price");
      int r_person = Integer.valueOf(req.getParameter("r_person"));
      String pnum = req.getParameter("pnum");
      int count = Integer.valueOf(req.getParameter("count"));
      ProdRoom pr = new ProdRoom();
      pr.setHtr_htmid(id);
      pr.setHtr_rnum(r_num);
      pr.setHtr_name(r_name);
      pr.setHtr_price(r_price);
      pr.setHtr_pnum(r_person);
      if(sDao.updateRoom(pr)==1){
         System.out.println("기존 방 수정 성공");
      }else{
         System.out.println("기존 방 수정 실패");
      }
      mav.addObject("pnum", pnum);
      mav.addObject("count", count);
      hotelUpdateFrm();
   }*/
   //숙박 상품 수정 페이지 이동
   private void hotelUpdateFrm() {
      String pnum = req.getParameter("pnum");
      int count = Integer.valueOf(req.getParameter("count"));
      String id = "HBB";//session.getAttribute("id").toString();
      ProdHotel ph = new ProdHotel();
      List<Image> imList = null;
      List<ProdRoom> prList = null;
      
      ph = sDao.selectUpFrm(pnum);
      prList = sDao.selectRoom(id);
      imList = sDao.selectName(pnum);
      
      System.out.println(ph.getHt_city());
      String updatehtml = make_html(ph, imList, prList, count);
      mav = new ModelAndView();
      mav.addObject("updatehtml", updatehtml);
      mav.addObject("pnum", pnum);
      mav.addObject("count", count);
      mav.setViewName("hotelupdate");
   }
   private String make_html(ProdHotel ph, List<Image> imList, List<ProdRoom> prList, int count) {
      StringBuilder sb=new StringBuilder();
      int i = 0;
      Image im = imList.get(i);
      sb.append("<img src='./resources/hotel/"+ im.getPi_sysname() +"'width='100px' name='image1'/>");
      sb.append("<div>");
      sb.append("<input type='file' name='mainfile' value='"+im.getPi_oriname()+"' onChange='fileChk(this)' />"); 
      sb.append("<input id='fileCheck1' type='hidden' name='fileCheck1' />");
      sb.append("</div>");
      sb.append("<div>");
      sb.append("<p name='nation'>국가명  : "+ph.getHt_nation()+"</p>");
      sb.append("<p name='city'>도시명  : "+ph.getHt_city()+"</p>");
      sb.append("호텔명(한국) <input type='text' value='"+ph.getHt_krname()+"' name='krname'/><br/>");
      sb.append("호텔명(영어) <input type='text' value='"+ph.getHt_egname()+"' name='egname'/><br/>");
      sb.append("연락처  : <input type='text' value='"+ph.getHt_msphone()+"' name='msphone'/><br/>");
      sb.append("주소  : <input type='text' value='"+ph.getHt_addr()+"' name='addr'/><br/>");
      sb.append("<h4>객실번호 수정 불가!</h4>");
      sb.append("<div id='frm'>");
      for(int a=0; a < prList.size(); a++){
         ProdRoom pr = prList.get(a);
         sb.append("<div id='ajaxroom'>객실번호 <input type='text' id='nval"+a+"' name='r_num"+a+"' value='"+pr.getHtr_rnum()+"' readonly/> 객실명 <input type='text' name='r_name"+a+"' value='"+pr.getHtr_name()+"'/> "); 
         sb.append("객실가격 <input type='text' name='r_price"+a+"' value='"+pr.getHtr_price()+"'/> 최대인원 <input type='text' name='r_person"+a+"' value='"+pr.getHtr_pnum()+"'/>");
         sb.append("<input type='button' value='삭제' onclick='deleteClick(this,"+count+", "+a+")'/>");
         sb.append("<input type='button' value='수정' onclick='updateClick(this,"+a+")'/><br/></div>");
      }
      sb.append("<input type='hidden' value='"+prList.size()+"' id='sizeval'");
      sb.append("</div>");
      sb.append("<input type='button' value='추가' id='omg'/>");
      sb.append("<div id='frm1'>");
      sb.append("</div>");
      im = imList.get(1);
      sb.append("<img src='./resources/hotel/"+ im.getPi_sysname() +"'width='100px' name='image2'/><br/>");
      sb.append("<div>");
      sb.append("<input type='file' name='subfile' onChange='fileChk(this)' />"); 
      sb.append("<input id='fileCheck2' type='hidden' name='fileCheck2' />");
      sb.append("</div>");
      im = imList.get(2);
      sb.append("<img src='./resources/hotel/"+ im.getPi_sysname() +"'width='100px' name='image3'/><br/>");
      sb.append("<div>");
      sb.append("<input type='file' name='etcfile' onChange='fileChk(this)' />"); 
      sb.append("<input id='fileCheck3' type='hidden' name='fileCheck3' />");
      sb.append("</div>");
      sb.append("<input type='button' value='수정하기' onclick='allupdateclick()'/>");
      sb.append("</div>");
      return sb.toString();
   }
   private void hotelDeleteFrm() {
      String id = session.getAttribute("id").toString();
      String pnum = req.getParameter("pnum");
      if(sDao.deleteProd(id, pnum) == 1){
         System.out.println("호텔 상품 삭제 완료");
      }else{
         System.out.println("호텔 상품 삭제 실패");
      }
   }
   private void getBestProdUpdate() {
      String part = req.getParameter("part");
      String ht_mid = req.getParameter("ht_mid");
      int t_num = Integer.valueOf(req.getParameter("t_num"));
      int result = 0;
      System.out.println(ht_mid);
      System.out.println(t_num);
      if(part.equals("숙박")){
         result = sDao.BestUpdateH(ht_mid);
      }else{
         result = sDao.BestUpdateT(t_num);
      }
      if(result == 0){
         System.out.println("수정실패");
      }else{
         System.out.println("수정성공");
      }
   }

   private void getSellerList() {
      //String val = req.getParameter("testvalue");   
      //System.out.println(val);
      //String id = session.getAttribute("id").toString();
      String id = "AAA";
      System.out.println(id);
      String part = "숙박";//sDao.selectPart(id);
      System.out.println(part);
      int prod  = sDao.selectProd(id);
      System.out.println(prod);

      System.out.println(id);
      mav = new ModelAndView();
      List<ProdHotel> hList = null;
      List<ProdTicket> tList = null;
      List<ReserveHotel> rhList = null;
      List<ReserveTicket> rtList = null;
      String sellerhtml = null;
      String memberhtml = null;
      if(part.equals("숙박")){
         hList = sDao.getHotelProdList(id);
         rhList = sDao.getHotelReserveList(id);
         sellerhtml = hlist_html(hList);
         memberhtml = rhList_html(rhList);
      }else{
         tList = sDao.getTicketProdList(id);
         rtList = sDao.getTicketReserveList(prod);
         sellerhtml = tlist_html(tList);
         memberhtml = rtList_html(rtList);
      }
      mav.addObject("sellerList", sellerhtml);
      mav.addObject("memberList", memberhtml);
      mav.addObject("part", part);
      mav.setViewName("sellerpage");
   }

   private String rhList_html(List<ReserveHotel> rhList) {
      StringBuilder sb=new StringBuilder();
      sb.append("<table><tr><td>호텔명(한글)</td><td>호텔명(영어)</td><td>국가명</td><td>도시명</td><td>연락처</td><td>주소</td><td>구분</td></tr>");
      for(int i=0; i<rhList.size(); i++){
         ReserveHotel rh = rhList.get(i);
         sb.append("<tr><td>"+rh.getRh_num()+"</td>");
         sb.append("<td>"+rh.getRh_mid()+"</td>");
         sb.append("<td>"+rh.getRh_checkin()+"</td>");
         sb.append("<td>"+rh.getRh_checkout()+"</td>");
         sb.append("<td>"+rh.getRh_htkrname()+"</td>");
         sb.append("<td>"+rh.getRh_htrname()+"</td>");
         sb.append("<td>"+rh.getRh_state()+"</td></tr>");
      }
      sb.append("</table>");
      return sb.toString();
   }

   private String rtList_html(List<ReserveTicket> rtList) {
      StringBuilder sb=new StringBuilder();
      sb.append("<table><tr><td>예약번호</td><td>상품번호</td><td>회원아이디</td><td>구매수량</td><td>예약/구매 날짜</td><td>총금액</td><td>사용가능여부</td></tr>");
      for(int i=0; i<rtList.size(); i++){
         ReserveTicket rt = rtList.get(i);
         sb.append("<tr><td>"+rt.getRt_num()+"</td>");
         sb.append("<td>"+rt.getRt_tnum()+"</td>");
         sb.append("<td>"+rt.getRt_mnid()+"</td>");
         sb.append("<td>"+rt.getRt_qty()+"</td>");
         sb.append("<td>"+rt.getRt_date()+"</td>");
         sb.append("<td>"+rt.getRt_total_price()+"</td>");
      }
      sb.append("</table>");
      return sb.toString();
   }

   private String hlist_html(List<ProdHotel> hList) {
      StringBuilder sb=new StringBuilder();
      sb.append("<table><tr><td>호텔명(한글)</td><td>호텔명(영어)</td><td>국가명</td><td>도시명</td><td>연락처</td><td>주소</td></tr>");
      for(int i=0; i<hList.size(); i++){
         ProdHotel h = hList.get(i);
         sb.append("<tr><td>"+h.getHt_krname()+"</td>");
         sb.append("<td>"+h.getHt_egname()+"</td>");
         sb.append("<td>"+h.getHt_nation()+"</td>");
         sb.append("<td>"+h.getHt_city()+"</td>");
         sb.append("<td>"+h.getHt_msphone()+"</td>");
         sb.append("<td>"+h.getHt_addr()+"</td>");
         sb.append("<td><a href='/bestProd?ht_mid="+ h.getHt_mid() +"'><input type='button' value='추천호텔등록'/></a></td></tr>");
      }
      sb.append("</table>");
      return sb.toString();
   }
   private String tlist_html(List<ProdTicket> tList) {
      StringBuilder sb=new StringBuilder();
      sb.append("<table><tr><td>상품번호</td><td>국가명</td><td>도시명</td><td>상품유형</td><td>상품명</td><td>성인가격</td><td>소인가격</td><td>사용기간</td><td>재고량</td></tr>");
      for(int i=0; i<tList.size(); i++){
         ProdTicket t = tList.get(i);
         sb.append("<tr><td>"+t.getT_num()+"</td>");
         sb.append("<td>"+t.getT_nation()+"</td>");
         sb.append("<td>"+t.getT_city()+"</td>");
         sb.append("<td>"+t.getT_type()+"</td>");
         sb.append("<td>"+t.getT_name()+"</td>");
         sb.append("<td>"+t.getT_aprice()+"</td>");
         sb.append("<td>"+t.getT_cprice()+"</td>");
         sb.append("<td>"+t.getT_start_date()+" ~ "+t.getT_end_date()+"</td>");
         sb.append("<td>"+t.getT_stock()+"</td>");
         sb.append("<td><a href='/bestProd?t_num="+ t.getT_num() + "'><input type='button' value='인기상품등록'/></a></td></tr>");
      }
      sb.append("</table>");
      return sb.toString();
   }

}