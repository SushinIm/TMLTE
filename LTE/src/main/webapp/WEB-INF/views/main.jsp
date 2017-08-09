<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LTE 여행사 메인 페이지</title>
<style>
</style>
</head>
<body>
	<ul class="horizonul">
		<li>
			<span>국내</span>
			<ul class="verticalul">
				<li><a href="airInternals">항공</a></li>
				<li><a href="hotelInternals">숙박</a></li>
				<li><a href="ticketInternals">티켓</a></li>			
			</ul>
		</li>
		<li>해외</li>
		<li>정보</li>
	</ul>
	
	<input type="hidden" value="판매자" name="m_part"/>
	<form name="mypageForm" method="get">
		<input type="button" value="판매자마이페이지" onclick="mypage(this)">
	</form>
</body>
<script>
	function mypage(elem){	//파일 element받음
		var frm = document.mypageForm;
	//if('${m_part}' == "판매자"){
		frm.action="mypageFrm";
		frm.submit();
	//}
	}//function End
</script>
</html>