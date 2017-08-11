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
			<p>행사 이름</p>
			<p>개최 국가, 도시</p>
			<p>성인 가격 : <input type="text" readonly="readonly" name="adultP" value="El1"/></p>
			<p>소아 가격 : <input type="text" readonly="readonly" name="childP" value="El2"/></p>
			<p>
				<input type="text" name="adultc" placeholder="성인 수" />
				<input type="text" name="childc" placeholder="소아 수" />
			</p>
			<p><input type="text" name="qty"><span>재고량 불러오기</span></p>
			<input type="hidden" value="6" name="prodnum"/>
			<input type="submit" value="결제하기"/>
		</form>
	</div>
	<div id="prodintr"></div>
	<div id="prodwarn"></div>
</body>
</html>
