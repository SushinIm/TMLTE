<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="resources/js/jquery-3.2.1.min.js"></script>
<style>
	#all{
		width: 50%; 
		height: 50%;
		padding: 5px 0 5px p; 
		margin:auto;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content-area">
<div class="container"><br><br>
<h4>마이페이지(일반)</h4><br><br><br>

<form name="mypagefrm" action="editinfobefore" method="post">
   <table id="all">
      <tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
      <tr>
         <th>아이디 : </th>
         <td>
            ${sessionScope.id}
         </td>
      </tr>
 	  <tr>
         <th>이름 : </th>
         <td>
         	${member.m_name}
         </td>
      </tr>
      <tr>
         <th>이메일 : </th>
         <td>
         	${member.m_email}
         </td>
      </tr>
       <tr>
         <th>생년월일 : </th>
         <td>
         	${member.m_birth}
         </td>
      </tr>
	  <tr>
 		 <th>전화번호 : </th>
         <td>
            ${member.m_phone}
         </td>
      </tr>
      <tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
      <tr>
         <td colspan="2" align="center"><br/><br/>
   			<button type="submit">정보수정</button>
   			<input type="button" value="홈으로" onclick="main(this)">
   		 </td>
      </tr>
   </table>
</form>
<br><br><br>
<div id="purchase"><button id="pbtn">내 거래내역</button></div>
	<div id="plist" style="display: none">
		${alist}
		${hlist}
		${tlist}
	</div>
</div>
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
		document.mypagefrm.action='main';
		document.mypagefrm.submit();
	}
</script>
</html>
<jsp:include page="footer.jsp" />