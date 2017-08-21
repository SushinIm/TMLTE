<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
<style>

</style>
</head>
<body>
<h1>회원정보수정 전 페이지</h1>
<form name="editbeforefrm" action="editinfo" method="post">
	<table width="500px" height="300px" style="padding: 5px 0 5px 0; ">
		<tr height="2" bgcolor="#000000"><td colspan="2"></td></tr>
		<tr>
	         <td>아이디  </td>
	         <td>
	            <input name="m_id" value="${sessionScope.id}" readonly="readonly" readonly="readonly" style="background-color:lightgray">
	         </td>
      	</tr>
 	  	<tr>
	         <td>비밀번호 확인 * </td>
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
</body>
<script>
	function main(obj){
		document.editbeforefrm.action='main';
	    document.editbeforefrm.submit();
	}
</script>
</html>