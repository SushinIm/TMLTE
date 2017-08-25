<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>판매자 블랙리스트</p>
${bslist}
<p>일반회원 블랙리스트</p>
${bblist}
<input type ="button" name = "돌아가기" value = "돌아가기" onclick = "location.href='./adminpage'">
</body>
<script>
	function deleteblack(b_mid){
		console.log(b_mid);
	
	location.href = "./deleteblack?b_mid="+b_mid;
	}
</script>

</html>