<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="prodimg"></div>
	<div id="prodinfo">
		<form action="ticketBuying" method="get">
			<p>숙박지 명(한글)</p>
			<p>숙박지 위치</p>
			<p>주소</p>
			<p>연락처</p>
		</form>
	</div>
	<div id="reserveplan">
		<input type="date" readonly="readonly" /> ~ <input type="date" readonly="readonly" />
	</div>
	<div id="reservereal">
		<table>
			<tr><th>객실명</th><th>객실수</th><th>가격</th><th>예약하기</th></tr>
			<tr><td>하늘방</td><td>4</td><td>50000</td><td><input type="button" value="실시간 예약"/></td></tr>
			<tr></tr>
		</table>
	</div>
</body>
</html>
