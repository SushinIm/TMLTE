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
<<<<<<< HEAD
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
=======
	<div id="header">
		<div id="home">
			<a href="main.jsp"><img src="homeimage"/></a>
		</div>
		<div id="searchbar">
			<input type="text" id="searchtext"/><a href="#imagesearch_layer" onclick="searchview()"></a>
		</div>
		
	</div>
	<div id="menu">
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
	</div>
	<div id="main">
	
	
	</div>
	<div id="footer">
	
	
	</div>
</body>
<script>
	function mypage(elem){	//파일 element받음
		var frm = document.mypageForm;
	//if('${m_part}' == "판매자"){
		frm.action="mypageFrm";
		frm.submit();
	//}
	}//function End

	
	function searchview(){
		$('#imagesearch_layer').addClass('open');
		$.ajax({
			type:'get',
			url:'contents',
			timeout:30000, //대기시간(3초)이 지날 경우 에러 상태
			//dataType:html,json
			success:function(data){
				$('#contents_layer').html(data);
			},
			error:function(error){
				alert("error");
				console.log(error);
			}
		}); //ajax End
	} //function End
	
	//LightBox 해제
	$(function(){ //$(documnet).ready(function(){})
		var layerWindow=$('#imagesearch_layer');
		layerWindow.find('#bg_layer').mousedown(
			function(enver){
				layerWindow.removeClass('open');
				return;
			}); //find End
		$(document).keydown(function(event){
			console.log(event); //ESC 키 번호	
			if(event.keyCode!=27) return;
			if(layerWindow.hasClass('open')){
				layerWindow.removeClass('open');
			}
		});//keydown End
	}); //function End
</script>
<div id="imagesearch_layer">
	<div id="bg_layer"></div>
	<div id="contents_layer"></div>
</div>

</html>