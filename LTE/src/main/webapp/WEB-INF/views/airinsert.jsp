<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script>
function airinsert(){
	document.airFrm.action="airinsert?";
	document.airFrm.submit();
}
</script>
</head>
<body>
<c:if test="${login eq '관리자'}">
	<form name="airFrm" id="formname1">
		항공기명 <input type="text" name="airname"/><br/>
		등급<input type="text" name="grade1" value="퍼스트" readonly/>
		행 <input type="text" name="frow"/>
		열 <input type="text" name="fcol"/><br/>
		등급<input type="text" name="grade2" value="비지니스" readonly/>
		행 <input type="text" name="brow"/>
		열 <input type="text" name="bcol"/><br/>
		등급<input type="text" name="grade3" value="이코노미" readonly/>
		행 <input type="text" name="erow"/>
		열 <input type="text" name="ecol"/><br/>
		<input type="button" value="기종등록" onclick="airinsert()"/>
	</form>
</c:if>
</body>
</html>