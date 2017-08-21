<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
<style></style>
</head>
<body>
<form name="joinendfrm" action="next" >
   <div width="940" style="padding: 5px 0 5px p; ">
      <div><h1>회원가입이 완료되었습니다.</h1></div><br/><br/>
      <div>상단의 로그인 버튼으로 로그인 하신 뒤에 본 서비스를 이용해 주시기 바랍니다.</div><br/><br/><br/>
   	  <input type="reset" value="홈으로" onclick="main(this)">
   </div>
</form>
</body>
<script>
	function main(obj){ //메인으로
	   document.joinendfrm.action='main';
   	   document.joinendfrm.submit();
	}
</script>
</html>