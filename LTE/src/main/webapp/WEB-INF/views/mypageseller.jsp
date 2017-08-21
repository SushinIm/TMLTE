<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="resources/js/jquery-3.2.1.min.js"></script>
<style>
</style>
</head>
<body>
<h1>판매자 마이페이지</h1>
<form name="mypagesellerfrm" action="editinfobeforeseller" method="post">
   <table width="500px" height="300px" style="padding: 5px 0 5px 0; ">
      <tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
      <tr>
         <td>아이디 : </td>
         <td>
            ${sessionScope.id}
         </td>
      </tr>
 	  <tr>
         <td>상호명 : </td>
         <td>
         	${member.m_name}
         </td>
      </tr>
      <tr>
         <td>이메일 : </td>
         <td>
         	${member.m_email}
         </td>
      </tr>
	  <tr>
 		 <td>전화번호 : </td>
         <td>
            ${member.m_phone}
         </td>
      </tr>
      <tr>
 		 <td>사업자번호 : </td>
         <td>
            ${member.m_bnum}
         </td>
      </tr>
      <tr>
 		 <td>판매자 구분 : </td>
         <td>
            ${member.m_part}
         </td>
      </tr>
      <tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
      <tr>
         <td colspan="2" align="center"><br/><br/>
   			<input type="submit" value="정보수정">
   			<input type="button" value="홈으로" onclick="main(this)">
			<input type="button" value="회원탈퇴" onclick="delete(this)">
   		 </td>
      </tr>
   </table>
</form>
	<div id="purchase"><button id="pbtn">내 거래내역</button></div>
	<div id="plist" style="display: none">
		${phlist}
		${ptlist}
	</div>
	<div id="registered"><button id="rbtn">내 상품리스트</button></div>
	<div id="rlist" style="display: none">
		${rhlist}
		${rhrlist}
		${rtlist}
	</div>
</body>
<script>
	$('#pbtn').click(function(){
		if($('#plist').css('display','none')){
			$('#plist').css('display','block');
		}else{
			$('#plist').css('display','none');
		}
	});
	
	function main(obj){ //메인으로
		document.mypagesellerfrm.action='main';
		document.mypagesellerfrm.submit();
	}
</script>
</html>