<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
<style>
	#all{
		width: 50%; 
		height: 50%;
		padding: 5px 0 5px p; 
		margin: auto;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content-area">
<div class="container"><br><br>
<h4>회원정보수정 전 페이지</h4><br><br><br>
<form name="editbeforesellerfrm" action="editinfoseller" method="post">
	<table id="all">
		<tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
		<tr>
	         <th>아이디  </th>
	         <td>
	            <input name="m_id" value="${sessionScope.id}" readonly="readonly" readonly="readonly" style="background-color:lightgray">
	         </td>
      	</tr>
 	  	<tr>
 	  	     <th>비밀번호 확인 * </th>
	         <td>
	         	<input type="password" name="m_pw">
	         </td>
      	</tr>
      	<tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
	  	<tr>
	         <td colspan="2" align="center"><br/><br/>
			 	<input type="submit" value="다음">
		     	<input type="reset" value="홈으로" onclick="main(this)">
		  	</td>
	  	</tr>
	</table>
</form>
<br><br><br>
</div>
</div>
</body>
<script>
	function main(obj){
		document.editbeforesellerfrm.action='main';
	    document.editbeforesellerfrm.submit();
	}
</script>
</html>
<jsp:include page="footer.jsp" />