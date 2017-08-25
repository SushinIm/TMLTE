<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:if test="${login eq '관리자'}">
			<form name="prodForm">
				<input id="sellerProd" type="button" value="항공기종등록"
					onclick="insertProdA()"> 
			</form>
			<form name="flightForm">
				<input type="button" value="항공상품등록" 
					onclick="flightprod()">
			</form>
		</c:if>
	</div>
</body>
<script>
function insertProdA() {
		document.prodForm.action = "airinsertFrm";
		document.prodForm.submit();
	}
function flightprod() {
	document.flightForm.action = "flightprod";
	document.flightForm.submit();
}	
</script>
</html>