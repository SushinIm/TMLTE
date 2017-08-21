<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-3.2.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<<<<<<< HEAD
	<div id="header">
	</div>
	<div id="section">
		<form>
			<div>
				<ul>
					<li>예약자 성명 : <input type="text" name="m_name" value="${mbInfo.m_name}" /></li>
					<li>예약자 연락처 : <input type="text" name="m_phone" value="${mbInfo.m_phone}" /></li>
					<li>예약자 이메일 : <input type="text" name="m_email" value="${mbInfo.m_email}" /></li>
					<li>예약자 생년월일 : <input type="text" name="m_birth" value="${mbInfo.m_birth}" /></li>
				</ul>
			</div>
			<div>
				<input type="button" value="탑승객 등록" />
			</div>
			<div id="seats">
				<input type="button" value="추가" onclick="formAdd()"/>
				<ul class="seatFrm">
					<li>
						등급 : <select>
							<option>퍼스트</option>
							<option>비즈니스</option>
							<option>이코노미</option>
						</select>
					</li>
					<li>
						좌석 열 : <select>
							<option>등급을 먼저 선택해 주십시오</option>
						</select>
					</li>
					<li>	
						좌석 행 : <select>
							<option>좌석 행을 먼저 선택해 주십시오</option>
						</select>
					</li>
					<li>
						<input type="text" value="탑승객 검색" />온클릭으로 팝업 띄우면서 ajax 실행시켜서 탑승객 목록 출력
					</li>
					<li>
						<input type="button" value="삭제" />	
					</li>
				</ul>
			</div>
		</form>
	</div>
	<div id="footer">
	</div>
</body>
<script type="text/javascript">
	/* 
	$(document).ready(){
	
	} 
	*/
	function formAdd(){
		$('.seatFrm').clone().appendTo('#seats');
	}
	
	function popupOpen(){
		var popUrl = "test.html";	//팝업창에 출력될 페이지 URL
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	}

</script>
=======

</body>
>>>>>>> 5dea1fb7d4188a66214634538c785d394404db0a
</html>