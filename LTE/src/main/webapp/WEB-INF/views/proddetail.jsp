<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="updateFrm" action="hotelUpdateFrm">
		<input type="hidden" value="${pnum}" name="pnum" />
		<input type="hidden" name="count" value="${count}"/>
		<input type="submit" value="수정" /> 
	</form>
	<form name="deleteFrm" action="hotelDeleteFrm">
		<input type="submit" value="삭제" /> 
	</form> 
	
</body>
</html>