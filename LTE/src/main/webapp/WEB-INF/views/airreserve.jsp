<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>좌석 선택</legend>
		<div>
			등급 : <select>
				<option>퍼스트</option>
				<option>비즈니스</option>
				<option>이코노미</option>
			</select>
			좌석 행 : <select>
				<option>등급을 먼저 선택해 주십시오</option>
			</select>
			좌석 열 : <select>
				<option>좌석 행을 먼저 선택해 주십시오</option>
			</select>
		</div>
		<input type="button" value="좌석 추가"/>
	</fieldset>
	
	<fieldset>
		<legend>예약자 정보</legend>
		<div>
			<ul>
				<li></li>
			</ul>
		</div>
	</fieldset>
</body>
</html>